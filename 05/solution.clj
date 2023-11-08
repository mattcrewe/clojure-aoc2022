(ns clojure-aoc.05.solution 
  (:require [clojure-aoc.core :refer [readfile]]
            [clojure.string :as string]))

(def input (readfile 5))

(defn encode [input]
  (let [[boardlines rulelines] (map #(string/split % #"\n") (string/split input #"\n\n"))
        rules (map #(map (fn [a] (Integer/parseInt a)) (take-nth 2 (drop 1 (string/split % #" ")))) rulelines)
        boardrows (map #(take-nth 4 (drop 1 %)) (drop 1 (reverse boardlines)))
        board (apply mapv #(remove (partial = \space) %&) boardrows)
        ]
    [board rules])
  )

(encode input)

(defn part1step [board [c f t]]
  (let [from (- f 1)
        to (- t 1)
        mov (take-last c (nth board from))
        taken (update board from (partial drop-last c))
        added (update taken to concat (reverse mov))]
    added))

(defn steps [input stepfn]
  (apply reduce stepfn input)
  )

(steps (encode input) part1step)

(defn decode [board]
  (apply str (map last board))
  )

(decode (steps (encode input) part1step))

(defn part2step [board [c f t]]
  (let [from (- f 1)
        to (- t 1)
        mov (take-last c (nth board from))
        taken (update board from (partial drop-last c))
        added (update taken to concat mov)]
    added))

(decode (steps (encode input) part2step))