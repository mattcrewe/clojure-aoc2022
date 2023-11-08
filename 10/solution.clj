(ns clojure-aoc.10.solution
  (:require [clojure-aoc.core :refer [readfile]]
            [clojure.string :as string]))

(def input (readfile 10))

(defn noop? [input]
  (= "noop" input))
  

;; (defn numornoop [input]
;;   (if noop? "noop" (Integer/parseInt input)))

(defn encode [input]
  (for [lines (string/split input #"\n")]
    (-> (string/split lines #" ")
        (last))))

(def example (encode "addx 15
addx -11
addx 6
addx -3
addx 5
addx -1
addx -8
addx 13
addx 4
noop
addx -1
addx 5
addx -1
addx 5
addx -1
addx 5
addx -1
addx 5
addx -1
addx -35
addx 1
addx 24
addx -19
addx 1
addx 16
addx -11
noop
noop
addx 21
addx -15
noop
noop
addx -3
addx 9
addx 1
addx -3
addx 8
addx 1
addx 5
noop
noop
noop
noop
noop
addx -36
noop
addx 1
addx 7
noop
noop
noop
addx 2
addx 6
noop
noop
noop
noop
noop
addx 1
noop
noop
addx 7
addx 1
noop
addx -13
addx 13
addx 7
noop
addx 1
addx -33
noop
noop
noop
addx 2
noop
noop
noop
addx 8
noop
addx -1
addx 2
addx 1
noop
addx 17
addx -9
addx 1
addx 1
addx -3
addx 11
noop
noop
addx 1
noop
addx 1
noop
noop
addx -13
addx -19
addx 1
addx 3
addx 26
addx -30
addx 12
addx -1
addx 3
addx 1
noop
noop
noop
addx -9
addx 18
addx 1
addx 2
noop
noop
addx 9
noop
noop
noop
addx -1
addx 2
addx -37
addx 1
addx 3
noop
addx 15
addx -21
addx 22
addx -6
addx 1
noop
addx 2
addx 1
noop
addx -10
noop
noop
addx 20
addx 1
addx 2
addx 2
addx -6
addx -11
noop
noop
noop
"))
     
   

(encode input)
example

(defn nextinstr [hist instr]
  (let [prev (last hist)
        noopinstr (fn [[cycle val]] [[(inc cycle) val]])
        addinstr (fn [[cycle val] add] [[(inc cycle) val][(+ cycle 2) (+ val (Integer/parseInt add))]])]
   (concat hist (if (noop? instr)
                  (noopinstr prev)
                  (addinstr prev instr)))))
  
(nextinstr [[1,1]] "noop")
(nextinstr [[1,1]] "15")

(defn signals [init instrs] 
  (reduce nextinstr [[1,init]] instrs))

(signals 1 (encode input))

(defn interesting [signals] 
  (take-nth 40 (drop 19 signals))
  )

(defn part1 [input]
  (apply + (map (partial apply *) (interesting (signals 1 input))))
  )


(part1 (encode input))




(defn hit? [[cycle sprite]] 
  (let [crt (mod (- cycle 1) 40)]
    (if (<= (abs (- crt sprite)) 1)
      "#"
      " ")))

(defn part2 [input]
  (map (partial apply str) (partition 40 (map hit? (signals 1 input))))
  )


(part2 (encode input))