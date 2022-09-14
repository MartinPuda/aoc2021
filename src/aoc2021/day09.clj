(ns aoc2021.day09
  (:require [clojure.string :as s])
  (:gen-class))

(defn get-input []
  ;"2199943210\n3987894921\n9856789892\n8767896789\n9899965678")
  (slurp "resources/public/input09.txt"))

(defn parse-line [line]
  (mapv #(Character/digit ^Character % 10) line))

(defn parse-data [data]
  (->> (s/split-lines data)
       (mapv parse-line)))

(defn is-low-point? [data y x]
  (let [num (get-in data [y x])]
    (and (> (get-in data [(dec y) x] ##Inf) num)
         (> (get-in data [y (dec x)] ##Inf) num)
         (> (get-in data [y (inc x)] ##Inf) num)
         (> (get-in data [(inc y) x] ##Inf) num))))

(defn part1 [data]
  (->> (for [[y line] (map-indexed vector data)
             [x n] (map-indexed vector line)
             :when (is-low-point? data y x)]
         n)
       (map inc)
       (reduce +)))

;(part1 (parse-data (get-input)))
;=> 478


