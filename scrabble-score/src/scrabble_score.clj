(ns scrabble-score
  (:require [clojure.string :as str]))

(defn invert [split-fn multimap]
  (letfn [(split-entry [[k v]]
            (map vector (split-fn v) (repeat k)))]
    (into {} (mapcat split-entry multimap))))

(def letter-scores
  (invert
    #(str/split % #"")
    {1 "AEIOULNRST"
     2 "DG"
     3 "BCMP"
     4 "FHVWY"
     5 "K"
     8 "JX"
     10 "QZ"}))

(def score-letter
  (comp letter-scores str/upper-case))

(defn score-word [word]
  (reduce + (map score-letter word)))
