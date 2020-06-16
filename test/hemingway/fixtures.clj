(ns hemingway.fixtures
  (:require [clojure.test :refer :all])
  (:import (org.apache.curator.test TestingServer)))

(defn setup-embedded-zookeeper [f]
  (let [server (TestingServer. 2181)]
    (do (f)
        (.close server))))

