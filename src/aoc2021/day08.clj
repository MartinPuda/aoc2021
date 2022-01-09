(ns aoc2021.day08
  (:require [clojure.string :as s]
            [clojure.set :as set])
  (:gen-class))

(def input
  (->> (->  (slurp "resources/public/input08.txt")
         ;"be cfbegad cbdgef fgaecd cgeb fdcge agebfd fecdb fabcd edb | fdgacbe cefdb cefbgd gcbe\nedbfga begcd cbg gc gcadebf fbgde acbgfd abcde gfcbed gfec | fcgedb cgb dgebacf gc\nfgaebd cg bdaec gdafb agbcfd gdcbef bgcad gfac gcb cdgabef | cg cg fdcagb cbg\nfbegcd cbd adcefb dageb afcb bc aefdc ecdab fgdeca fcdbega | efabcd cedba gadfec cb\naecbfdg fbg gf bafeg dbefa fcge gcbea fcaegb dgceab fcbdga | gecf egdcabf bgf bfgea\nfgeab ca afcebg bdacfeg cfaedg gcfdb baec bfadeg bafgc acf | gebdcfa ecba ca fadegcb\ndbcfg fgd bdegcaf fgec aegbdf ecdfab fbedc dacgb gdcebf gf | cefg dcbef fcge gbcadfe\nbdfegc cbegaf gecbf dfcage bdacg ed bedf ced adcbefg gebcd | ed bcgafe cdgba cbgef\negadfb cdbfeg cegd fecab cgb gbdefca cg fgcdab egfdb bfceg | gbdfcae bgc cg cgb\ngcafb gcf dcaebfg ecagb gf abcdeg gaef cafbge fdbac fegbdc | fgae cfgab fg bagce"
         s/split-lines)
       (map #(s/split % #"\|"))))

(defn part1 [data]                                          ;365
  (->> data
       (map second)
       (map s/trim)
       (mapcat #(s/split % #" "))
       (filter #(#{2 3 4 7} (count %)))
       (count)))

;(defn displays-to-strings [s1]
;  (-> s1
;      s/trim
;      (s/split #" ")))
;
;(defn segments-to-numbers [seg-string]
;  (prn seg-string)
;  (let [seg (-> seg-string
;                s/trim
;                (s/split #" "))
;        one (some #(= 2 (count %)) seg)
;        seven (some #(= 3 (count %)) seg)
;        four (some #(= 4 (count %)) seg)
;        eight (some #(= 8 (count %)) seg)
;        two-three-five (filter #(= 5 (count %)) seg)
;        zero-six-nine (filter #(= 6 (count %)) seg)
;        two (some #(= 2 (set/intersection (set %) (set four))) two-three-five)
;        three (some #(= 3 (set/intersection (set %) (set seven))) two-three-five)
;        five (->> two-three-five
;                  (remove #{two three})
;                  (first))
;        nine (some #(= 5 (set/intersection (set %) (set three))) zero-six-nine)
;        zero (some #(= 3 (set/intersection (set %) (set seven))) zero-six-nine)
;        six (->> zero-six-nine
;                 (remove #{zero nine})
;                 (first))]
;    (zipmap [zero one two three four five six seven eight nine] (range 10))))
;
;
;
;(defn part2 [data]
;  (->> data
;       (map (fn [[segments displays]]
;              [(segments-to-numbers segments)
;               (displays-to-strings displays)]))
;       (mapcat (fn [[seg strings]] (map seg strings)))
;       (apply +)))