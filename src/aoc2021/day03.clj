(ns aoc2021.day03
  (:require [clojure.string :as s])
  (:gen-class))

(defn get-input []
  (->> (slurp "resources/public/input03.txt")
       (s/split-lines)))

(defn gamma-rate [data]
  (map #(->> (frequencies %)
             (sort-by val)
             second
             first)
       data))

(defn epsilon-rate [data]
  (map #(->> (frequencies %)
             (sort-by val)
             ffirst)
       data))

(defn vector->binary [v]
  (Integer/parseInt (s/join "" v) 2))

(defn part1 [data]
  (let [columns (apply map vector data)]
    (->> columns
         ((juxt gamma-rate epsilon-rate))
         (map vector->binary)
         (apply *))))

(defn rating [position data type]
  (let [columns (apply map vector data)]
    (if (= (count data) 1)
      (first data)
      (let [f (frequencies (nth columns position))
            most-common (if (= (f \0) (f \1))
                          (if (= type :oxygen) \1 \0)
                          (ffirst (sort-by val (if (= type :oxygen) > <) f)))]
        (recur (inc position)
               (filter #(= (nth % position) most-common) data)
               type)))))

(defn part2 [data]
  (* (vector->binary (rating 0 data :oxygen))
     (vector->binary (rating 0 data :CO2))))