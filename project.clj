(defproject cascalog-examples "0.1.0-SNAPSHOT"
  :description "Cascalog examples"
  :url "http://github.com/tiensonqin/cascalog-examples"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [cascalog/cascalog-core "2.1.0"]
                 [clojure-hbase "0.92.3"]
                 [org.clojure/tools.logging "0.3.0"]]
  :profiles { :dev {:dependencies [[org.apache.hadoop/hadoop-core "1.1.2"]]}}
  :jvm-opts ["-Xms768m" "-Xmx768m"])
