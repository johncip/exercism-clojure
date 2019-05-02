(ns scrabble-score)

(defn invert [multimap]
  (into {} (mapcat
             (fn [[k v]] (map vector (seq v) (repeat k)))
             multimap)))

(def values-by-letter
  (invert {1 "AEIOULNRST"
           2 "DG"
           3 "BCMP"
           4 "FHVWY"
           5 "K"
           8 "JX"
           10 "QZ"}))

(defn score-letter [letter]
  (-> letter
      (#(if (string? %) (first %) %))
      (#(Character/toUpperCase %))
      values-by-letter))

(defn score-word [word]
  (reduce + (map score-letter word)))
