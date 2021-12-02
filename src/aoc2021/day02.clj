(ns aoc2021.day02
  (:require [clojure.string :as s])
  (:gen-class))

(defn get-input []
  (->> (slurp "resources/public/input02.txt")
       (s/split-lines)
       (map #(s/split % #" "))
       (map (fn [[direction value]]
              [(keyword direction) (Integer/parseInt value)]))))

(defn mul-pos [state]
  (* (:x state)
     (:depth state)))

(defn part1 [data]
  (let [state (reduce (fn [state [direction value]]
                        (case direction
                          :forward (update state :x + value)
                          :up (update state :depth - value)
                          :down (update state :depth + value)))
                      {:x 0 :depth 0}
                      data)]
    (mul-pos state)))

(defn part2 [data]
  (let [state (reduce (fn [state [direction value]]
                        (case direction
                          :forward (-> state
                                       (update :x + value)
                                       (update :depth + (* (:aim state) value)))
                          :up (update state :aim - value)
                          :down (update state :aim + value)))
                      {:x 0 :depth 0 :aim 0}
                      data)]
    (mul-pos state)))