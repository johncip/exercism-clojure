(ns minesweeper
  (:require [clojure.string :refer [split join]]))

(defn put-when [coll k v]
  (if (contains? coll k) (assoc coll k v) coll))

(defn get-g [grid row col]
  (nth (nth grid row nil) col nil))

(defn update-g [grid row col f]
  (let [old (get-g grid row col)
        new (f old)]
    (put-when grid row
         (put-when (nth grid row nil) col new))))

(defn coords [r0 r1 c0 c1]
  (let [rs (range r0 (inc r1))
        cs (range c0 (inc c1))]
    (for [r rs c cs] (vector r c))))

(defn inc-square [v]
  (cond (= v \*) \*
        (= v \space) 1
        (number? v) (inc v)))

(defn mark-around [board [row col]]
  (let [v (get-g board row col)]
    (if-not (= v \*)
      board
      (reduce
        (fn [b [r c]] (update-g b r c inc-square))
        board
        (coords (dec row) (inc row) (dec col) (inc col))))))

(defn normalize [board]
  (mapv #(apply vector %) (split board #"\n")))

(defn draw [board]
  (let [b (normalize board)
        coords (coords 0 (count b) 0 (count (first b)))]
    (->> (reduce mark-around b coords)
         (map join)
         (join "\n"))))
