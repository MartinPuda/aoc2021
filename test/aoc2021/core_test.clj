(ns aoc2021.core-test
  (:require [clojure.test :refer :all]
            [aoc2021.core :refer :all]
            [aoc2021.day01 :as day01]))

(deftest day01
  (testing "Part 1"
    (is (= (day01/part1) 1529)))
  (testing "Part 2"
    (is (= (day01/part2) 1567))))
