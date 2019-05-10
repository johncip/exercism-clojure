(ns queen-attack
  (:require [clojure.string :refer [join]]))

(defn index [[row col]]
  (+ (* 8 row) col))

(defn board-string [mp]
  (if (empty? mp)
    (join (repeat 8 "_ _ _ _ _ _ _ _\n"))
    (let [w (index (:w mp))
          b (index (:b mp))]
      (->> (range 0 64)
           (mapv #({w \W b \B} % \_))
           (partition 8)
           (map #(str (join " " %) "\n"))
           join))))

(def disp
  (comp #(Math/abs %) -))

(defn can-attack [{w :w b :b}]
  (let [[dx dy] (map disp w b)]
    (or (zero? dx) (zero? dy) (= dx dy))))
