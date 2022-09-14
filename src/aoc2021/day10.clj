(ns aoc2021.day10
  (:require [clojure.string :as s])
  (:gen-class))

(defn get-input []
  ;"[({(<(())[]>[[{[]{<()<>>\n[(()[<>])]({[<{<<[]>>(\n{([(<{}[<>[]}>{[]{[(<()>\n(((({<>}<{<{<>}{[]{[]{}\n[[<[([]))<([[{}[[()]]]\n[{[{({}]{}}([{[{{{}}([]\n{<[[]]>}<{[{[{[]{()[[[]\n[<(<(<(<{}))><([]([]()\n<{([([[(<>()){}]>(<<{{\n<{([{{}}[<[[[<>{}]]]>[]]")
  (slurp "resources/public/input10.txt"))

(defn parse-data [data]
  (s/split-lines data))

(defn pair? [p1 p2]
  (= p2 ({\( \) \[ \] \{ \} \< \>} p1)))

(defn corrupted? [line]
  (reduce (fn [result c]
            (cond (empty? result) (conj result c)
                  (pair? (peek result) c) (pop result)
                  (#{\( \[ \{ \<} c) (conj result c)
                  :else (reduced c)))
          []
          line))

(def rating {\) 3 \] 57 \} 1197 \> 25137})

(defn part1 []
  (->> (parse-data (get-input))
       (map corrupted?)
       (filter char?)
       (map rating)
       (reduce +)))
