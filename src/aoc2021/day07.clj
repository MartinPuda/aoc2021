(ns aoc2021.day07
  (:require [clojure.string :as s])
  (:gen-class))

(def input
  (->> (-> "resources/public/input07.txt"
           slurp
           s/trim-newline
           (s/split #","))
       (map parse-long)))

(defn lowest-fuel [input fuel-cost]
  (let [mn (apply min input)
        mx (apply max input)]
    (->> (for [i (range mn (inc mx))]
           (->> input
                (map #(fuel-cost (abs (- i %))))
                (apply +)))
         (apply min))))

(defn part1 [input] ;349357
  (lowest-fuel input identity))

(defn part2 [input] ;96708205
  (lowest-fuel input (memoize #(/ (* % (inc %)) 2))))




