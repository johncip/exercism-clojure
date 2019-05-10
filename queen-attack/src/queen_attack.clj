(ns queen-attack
  (:require [clojure.string :as str]))

(defn index [[row col]]
  (+ (* 8 row) col))

(defn board-string [mp]
  (if (empty? mp)
    (apply str (repeat 8 "_ _ _ _ _ _ _ _\n"))
    (let [w (index (:w mp))
          b (index (:b mp))]
      (->> (range 0 64)
           (mapv #({w \W b \B} % \_))
           (partition 8)
           (map #(str (str/join " " %) "\n"))
           str/join))))

(defn disp [a b]
  (Math/abs (- a b)))

(defn can-attack [{w :w b :b}]
  (let [[dx dy] (map disp w b)]
    (or (zero? dx) (zero? dy) (= dx dy))))
