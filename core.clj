(ns clojure-aoc.core
  (:gen-class) 
  )

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))


(defn readfile [day] (slurp (str "src/clojure_aoc/" (format "%02d" day) "/input.txt")))
