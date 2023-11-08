(ns clojure-aoc.03.solution
  (:require [clojure-aoc.core :refer [readfile]]
            [clojure.set :refer [intersection union]]
            [clojure.string :as string]))

(defn encode [input]
  (for [bag (string/split input #"\n")]
    (let [bagsize (count bag)
          pouchsize (/ bagsize 2)]
      [(set (take pouchsize bag)) (set (drop pouchsize bag))]))
  )

(encode (readfile 3))

(defn finddupes [bags]
  (for [[pouch1 pouch2] bags]
    (intersection pouch1 pouch2)
    )
  )

(finddupes (encode (readfile 3)))
(defn scoreitem [item]
  (let [itemcode (int (first item))
        itemidx (- itemcode (int \A))
        shifted (- itemidx 32)
        reranged (mod shifted 58)]
    (+ reranged 1)))

(map scoreitem (finddupes (encode (readfile 3))))

(apply + (map scoreitem (finddupes (encode (readfile 3)))))

(defn combinepouches [bags]
  (map (partial apply union) bags)
  )


(combinepouches (encode (readfile 3)))

(defn findbadge [bags]
  (let [combibags (combinepouches bags)] 
    (for [bagde (partition 3 combibags)]
      (apply intersection bagde)
      ))
  )

(apply + (map scoreitem(findbadge (encode (readfile 3)))))