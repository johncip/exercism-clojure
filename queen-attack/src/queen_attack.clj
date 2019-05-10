(ns queen-attack
  (:require [clojure.string :as str]))

(defn index [[row col]]
  (+ (* 8 row) col))

(defn format-row [chrs]
  (conj (vec (interpose \space chrs)) \newline))

(defn board-string [mp]
  (if (empty? mp)
    (apply str (repeat 8 "_ _ _ _ _ _ _ _\n"))
    (let [w (index (:w mp))
          b (index (:b mp))]
      (->> (range 0 64)
           (mapv #({w \W b \B} % \_))
           (partition 8)
           (mapcat format-row)
           (apply str)))))

(defn disp [a b]
  (Math/abs (- a b)))

(defn can-attack [{[wy wx] :w [by bx] :b}]
  (let [dx (disp wx bx)
        dy (disp wy by)]
    (or (zero? dx) (zero? dy) (= dx dy))))
