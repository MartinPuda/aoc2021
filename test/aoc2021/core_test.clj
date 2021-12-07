(ns aoc2021.core-test
  (:require [clojure.test :refer :all]
            [aoc2021.core :refer :all]
            [aoc2021.day01 :as day01]
            [aoc2021.day02 :as day02]
            [aoc2021.day03 :as day03]
            [aoc2021.day04 :as day04]
            [aoc2021.day05 :as day05]))

(deftest day01
  (testing "Part 1"
    (is (= (day01/part1 (day01/get-input)) 1529)))
  (testing "Part 2"
    (is (= (day01/part2 (day01/get-input)) 1567))))

(deftest day02
  (testing "Part1"
    (is (= (day02/part1 (day02/get-input)) 2272262)))
  (testing "Part2"
    (is (= (day02/part2 (day02/get-input)) 2134882034))))

(deftest day03
  (testing "Part1"
    (is (= (day03/part1 (day03/get-input)) 3969000)))
  (testing "Part2"
    (is (= (day03/part2 (day03/get-input)) 4267809))))

(deftest day04
  (testing "Part1"
    (is (= (day04/part1 (day04/get-input)) 38913)))
  (testing "Part2"
    (is (= (day04/part2 (day04/get-input)) 16836))))

(deftest day05
  (testing "Part1"
    (is (= (day05/part1 (day05/get-input)) 5167)))
  (testing "Part2"
    (is (= (day05/part2 (day05/get-input)) 17604))))
