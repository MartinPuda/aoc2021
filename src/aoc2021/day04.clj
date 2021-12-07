(ns aoc2021.day04
  (:require [clojure.string :as s])
  (:gen-class))

(defn parse-nums [data]
  (->> (s/split data #",")
       (map #(Integer/parseInt %))))

(defn parse-bingos [data]
  (->> data
       (remove s/blank?)
       (s/join " ")
       (#(s/split % #"\s+"))
       (map #(Integer/parseInt %))
       (partition 25)))

(defn get-input []
  (let [[nums & bingos] (->> (slurp "resources/public/input04.txt")
                             ;"7,4,9,5,11,17,23,2,0,14,21,24,10,16,13,6,15,25,12,22,18,20,8,19,3,26,1\n\n22 13 17 11  0\n 8  2 23  4 24\n21  9 14 16  7\n 6 10  3 18  5\n 1 12 20 15 19\n\n 3 15  0  2 22\n 9 18 13 17  5\n19  8  7 25 23\n20 11 10 24  4\n14 21 16 12  6\n\n14 21 17 24  4\n10 16 15  9 19\n18  8 23 26 20\n22 11 13  6  5\n 2  0 12  3  7"
                             (s/split-lines))]
    {:nums   (parse-nums nums)
     :bingos (parse-bingos bingos)}))

(defn mark-number [bingo number]
  (map #(if (= % number) "*" %) bingo))

(defn board-score [last-num bingo]
  (->> bingo
       (remove string?)
       (apply +)
       (* last-num)))

(defn check-bingo [bingo]
  (or (some #(every? string? %) (partition 5 bingo))
      (some #(every? string? %) (->> bingo
                                     (partition 5)
                                     (apply map vector)))))

(defn part1 [data]
  (reduce (fn [bingos num]
            (let [new-bingos (map #(mark-number % num) bingos)]
              (if (some check-bingo new-bingos)
                (->> (filter check-bingo new-bingos)
                     (first)
                     (board-score num)
                     (reduced))
                new-bingos)))
          (:bingos data)
          (:nums data)))

(defn part2 [data]
  (reduce (fn [bingos num]
            (let [new-bingos (->> bingos
                                  (map #(mark-number % num)))]
              (if (and (= (count new-bingos) 1)
                       (check-bingo (first new-bingos)))
                (->> (first new-bingos)
                     (board-score num)
                     (reduced))
                (remove check-bingo new-bingos))))
          (:bingos data)
          (:nums data)))
