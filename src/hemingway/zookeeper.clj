(ns hemingway.zookeeper
  (:require [mount.core :refer [defstate]]
            [zookeeper :as zk]
            [zookeeper.data :as data]
            [hemingway.domain.broker :refer :all])
  (:import (org.apache.zookeeper KeeperException$NoNodeException)))

(def brokerIdsPath "/brokers/ids")

(defstate zkclient :start (zk/connect "127.0.0.1:2181")
  :stop (zk/close zkclient))

(defn register-broker [broker]
  (try
    (zk/create zkclient (str brokerIdsPath "/" (:id broker)) :data (data/to-bytes (pr-str broker)))
    (catch KeeperException$NoNodeException e
      (zk/create-all zkclient brokerIdsPath :persistent? true)
      (register-broker broker))))


(defn get-brokers []
  (zk/get-acl zkclient brokerIdsPath))
