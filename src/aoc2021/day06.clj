(ns aoc2021.day06
  (:require [clojure.string :as s])
  (:gen-class))

(defn get-input []
  (->> (-> (slurp "resources/public/input06.txt")
           (s/trim-newline)
           (s/split #","))
       (map parse-long)))

(defn part [days]
  (->> (get-input)
       frequencies
       (reduce-kv (fn [c k v] (assoc c k v)) (vec (repeat 9 0)))
       (iterate (fn [[a b c d e f g h i]]
                  [b c d e f g (+ h a) i a]))
       (drop days)
       first
       (apply +)))
