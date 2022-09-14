(ns aoc2021.day01
  (:require [clojure.string :as s])
  (:gen-class))

(defn get-input []
  (->> (slurp "resources/public/input01.txt")
       (s/split-lines)
       (map parse-long)))

(defn part1 [data]
  (->> (partition 2 1 data)
       (filter #(apply < %))
       count))

(defn part2 [data]
  (->> (partition 3 1 data)
       (map #(apply + %))
       (part1)))