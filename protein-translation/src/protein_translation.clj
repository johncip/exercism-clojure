(ns protein-translation
  (:require [clojure.string :refer [join]]))

(defn invert [mm]
  (into {} (for [k (keys mm), v (mm k)] [v k])))

(def translate-codon
  (invert
    {"Methionine"    ["AUG"]
     "Tryptophan"    ["UGG"]
     "Phenylalanine" ["UUU" "UUC"]
     "Leucine"       ["UUA" "UUG"]
     "Serine"        ["UCU" "UCC" "UCA" "UCG"]
     "Tyrosine"      ["UAU" "UAC"]
     "Cysteine"      ["UGU" "UGC"]
     "STOP"          ["UAA" "UAG" "UGA"]}))

(defn translate-rna [rna]
  (->> rna
       (partition 3)
       (map (comp translate-codon join))
       (take-while #(not= "STOP" %))))
