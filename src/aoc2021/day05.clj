(ns aoc2021.day05
  (:require [clojure.string :as s])
  (:gen-class))

(defn parse-line [data]
  (->> (s/split data #" -> ")
       (map #(s/split % #","))
       (mapcat (fn [[x y]] [(parse-long x) (parse-long y)]))))

(defn horizontal-or-vertical? [[x1 y1 x2 y2]]
  (or (= x1 x2)
      (= y1 y2)))

(defn get-input []
  (->> (slurp "resources/public/input05.txt")
       ;"0,9 -> 5,9\n8,0 -> 0,8\n9,4 -> 3,4\n2,2 -> 2,1\n7,0 -> 7,4\n6,4 -> 2,0\n0,9 -> 2,9\n3,4 -> 1,4\n0,0 -> 8,8\n5,5 -> 8,2"
       (s/split-lines)
       (map parse-line)))

(defn make-grid []
  (->> (repeat 1000 0)
       (vec)
       (repeat 1000)
       (vec)))

(defn draw-line-to-grid [grid [x1 y1 x2 y2]]
  (reduce (fn [grid [y x]]
            (assoc-in grid [y x]
                      (if (#{1 2} (get-in grid [y x])) 2 1)))
          grid
          (map vector
               (cond (= y1 y2) (repeat y1)
                     (> y1 y2) (range y1 (dec y2) -1)
                     :else (range y1 (inc y2)))
               (cond (= x1 x2) (repeat x1)
                     (> x1 x2) (range x1 (dec x2) -1)
                     :else (range x1 (inc x2))))))

(defn count-elem [elem grid]
  (->> (apply concat grid)
       (filter #{elem})
       (count)))

(defn draw-lines [lines grid]
  (reduce draw-line-to-grid grid lines))

(defn part1 [data]
  (->> (draw-lines (filter horizontal-or-vertical? data)
                   (make-grid))
       (count-elem 2)))

(defn part2 [data]
  (->> (draw-lines data (make-grid))
       (count-elem 2)))

