(ns org.clojars.punit-naik.named-capture-groups-test
  (:require [clojure.test :refer [deftest is]]
            [org.clojars.punit-naik.named-capture-groups :as core]))

(defonce named-capture-groups-re #"(?<year>\d+)\-(?<month>\d+)\-(?<day>\d+)")
(defonce capture-groups-re #"(\d+)\-(\d+)\-(\d+)")

(deftest capture-group-names-test
  (is (= ["year" "month" "day"]
         (core/capture-group-names named-capture-groups-re)))
  (is (not (seq (core/capture-group-names capture-groups-re)))))

(deftest named-capture-groups-test
  (is (= {:year "2023", :month "01", :day "01"}
         (core/named-capture-groups named-capture-groups-re "2023-01-01")))
  (is (nil? (core/named-capture-groups named-capture-groups-re "fail to match"))))
