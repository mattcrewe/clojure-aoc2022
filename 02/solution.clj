(ns clojure-aoc.02.solution
  (:require [clojure-aoc.core :refer [readfile]]
            [clojure.string :as string]))

(defn encode [input]
  (for [game (string/split input #"\n")]
    (let [[a b] (string/split game #" ")]
      [(- (int (.charAt a 0)) (int \A))
       (- (int (.charAt b 0)) (int \X))])))

(defn game1_score [[a b]]
  (-> (- b a)
      (+ 1) 
      (mod 3)
      (* 3)
      (+ b 1)))

(reduce + (map game1_score (encode (readfile 2))))

(defn game2_score [[a b]]
  (-> (- b 1)
      (+ a)
      (mod 3)
      (+ 1 (* b 3))))

(reduce + (map game2_score (encode (readfile 2))))
