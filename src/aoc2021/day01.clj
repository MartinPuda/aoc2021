(ns aoc2021.day01
  (:require [clojure.string :as s])
  (:gen-class))

(defn get-input []
  (->> (slurp "resources/public/input01.txt")
       (s/split-lines)
       (map #(Integer/parseInt %))))

(defn part1 []
  (->> (get-input)
       (partition 2 1)
       (filter (fn [[a b]] (> b a)))
       count))

(defn part2 []
  (->> (get-input)
       (partition 3 1)
       (map #(apply + %))
       (partition 2 1)
       (filter (fn [[a b]] (> b a)))
       count))




