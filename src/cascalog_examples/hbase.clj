(ns cascalog-examples.hbase
  (:require [cascalog.api :refer :all]
            [cascalog.playground :refer :all]
            [cascalog.cascading.util :refer [fields]])
  (:import [com.twitter.maple.hbase HBaseScheme HBaseTap]
           [org.apache.hadoop.hbase.util Bytes]))

;; install hbase-0.92.1

;; redirect output to repl buffer
(bootstrap-emacs)

(defn stringify
  [bs]
  (-> bs
      (Bytes/toString)))

(defn hbase-tap [table-name key-field column-family & value-fields]
  (let [scheme (HBaseScheme. (fields key-field) column-family (fields value-fields))]
    (HBaseTap. table-name scheme)))

(def tap (hbase-tap "users" "?user" "cf" "?age"))

;; create hbase table 'users' firstly
;; create 'users', 'cf'

;; write age to hbase
(?<- tap
     [?user ?age]
     (age ?user ?age))

;; query from hbase
(?<- (stdout)
     [?user ?age]
     (tap ?user-bytes ?age-bytes)
     (stringify ?user-bytes :> ?user)
     (stringify ?age-bytes :> ?age))
