(ns clojure-aoc.04.solution 
  (:require [clojure-aoc.core :refer [readfile]]
            [clojure.string :as string]))

(def input (readfile 4))

(def example "2-4,6-8
2-3,4-5
5-7,7-9
2-8,3-7
6-6,4-6
2-6,4-8
" )

(defn encode [input]
  (for [line (string/split input #"\n")]
    (for [bounds (string/split line #",")]
      (for [bound (string/split bounds #"-")]
        (Integer/parseInt bound)))))

(encode example)
(encode input)

(defn fullycontains [bounds]
  (let [[[a b] [c d]] bounds]
    (or (and (<= a c) (>= b d)) (and (<= c a) (>= d b)))))

(count (filter true? (map fullycontains (encode example))))
(count (filter true? (map fullycontains (encode input))))

(defn partialcontains [bounds]
  (let [[[a b] [c d]] bounds]
    (not (or (< b c) (< d a)))))

(count (filter true? (map partialcontains (encode input))))