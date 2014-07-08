(ns cascalog-examples.lfs
  (:require [cascalog.api :refer :all]
            [clojure.string :as str]))

;; audit log from http://ianrumford.github.io/blog/2012/09/29/using-cascalog-for-extract-transform-and-load/

(defn sf [pattern line]
  (second (re-find pattern line)))

(defmapfn parse
  [line]
  (vector (sf #"type=([\w]+)" line)
          (sf #"node=([\w]+)" line)
          (sf #"msg=([\w\(.]+:[\d\)]+)" line)))

(defn print-fields
  [log]
  (let [tap (lfs-textline log)]
    (?<- (stdout)
         [?type ?node ?msg]
         (tap ?line)
         (parse ?line :> ?type ?node ?msg))))

(defn -main
  "Entry point"
  [log-path]
  (println "Printing fields in file" log-path)
  (print-fields log-path))
