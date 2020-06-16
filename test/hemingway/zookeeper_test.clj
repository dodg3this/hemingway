(ns hemingway.zookeeper-test
  (:require [clojure.test :refer :all])
  (:require [hemingway.zookeeper :refer :all])
  (:require [hemingway.fixtures :refer :all]))

(use-fixtures :once setup-embedded-zookeeper)


