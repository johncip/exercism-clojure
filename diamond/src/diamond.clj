(ns diamond
  (:require [clojure.string :as str]))

(defn letter [i]
  (char (+ (int \A) i)))

(defn mirror [xs]
  (concat xs (rest (reverse xs))))

(defn line [len i]
  (-> (vec (repeat len \space))
      (assoc (- len i 1) (letter i))
      mirror
      str/join))

(defn diamond [c]
  (let [len (inc (- (int c) (int \A)))]
    (mirror (map #(line len %) (range len)))))
