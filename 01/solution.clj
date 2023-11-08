(ns clojure-aoc.01.solution
  (:require [clojure-aoc.core :refer [readfile]]
            [clojure.string :as string]))

(defn encode [input]
  (for [pack (string/split input #"\n\n")]
    (for [item (string/split pack #"\n")]
      (Integer/parseInt item))))

(defn sum-order [input]
  (reverse (sort (map #(reduce + %) input))))

(defn part1 []
  (first (sum-order (encode (readfile 1)))))

(part1)

(defn part2 []
  (reduce + (take 3 (sum-order (encode (readfile 1))))))

(part2)