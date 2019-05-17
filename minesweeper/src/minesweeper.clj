(ns minesweeper
  (:require [clojure.string :refer [split join]]))

(defn put [coll k v]
  (if (contains? coll k)
    (assoc coll k v)
    coll))

(defn get_ [g row col]
  (nth (nth g row nil) col nil))

(defn put_ [g row col v]
  (put g row (put (nth g row nil) col v)))

(defn update_ [g row col f]
  (let [v (get_ g row col)]
    (put_ g row col (f v))))

(defn coords [g]
  (let [rs (range (count g))
        cs (range (count (first g)))]
    (for [r rs c cs] (vector r c))))

(defn inc-square [v]
  (cond (= v \*) \*
        (= v \space) 1
        (number? v) (inc v)))

(defn mark-around [board [row col]]
  (let [v (get_ board row col)
        mine? (= v \*)]
    (if-not mine?
      board
      (-> board
          (update_ (dec row) (dec col) inc-square)
          (update_ (dec row) col inc-square)
          (update_ (dec row) (inc col) inc-square)
          (update_ row (dec col) inc-square)
          (update_ row (inc col) inc-square)
          (update_ (inc row) (dec col) inc-square)
          (update_ (inc row) col inc-square)
          (update_ (inc row) (inc col) inc-square)))))

(defn normalize [board]
  (mapv #(apply vector %) (split board #"\n")))

(defn draw [board]
  (as-> board b
    (normalize b)
    (reduce mark-around b (coords b))
    (map join b)
    (join "\n" b)))
