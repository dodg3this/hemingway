(ns hemingway.zookeeper-test
  (:require [clojure.test :refer :all]
            [hemingway.zookeeper :as zk]
            [hemingway.fixtures :refer :all]
            [mount.core :as mount]
            [hemingway.domain.broker :refer :all]))

(use-fixtures :once setup-embedded-zookeeper)

(defn- test-wrapper [f]
  (mount/start #'zk/zkclient)
  (f)
  (mount/stop))

(use-fixtures :each test-wrapper)

(deftest client-test
  (testing "should register brokers with zookeeper"
    (zk/register-broker (->Broker 1 "10.10.10.10" 8000))
    (zk/register-broker (->Broker 2 "10.10.10.11" 8000))
    (is (= 2 (count (zk/get-brokers))))))
