(ns minesweeper
  (:require [clojure.string :refer [split-lines join]]))

(defn to-grid [board]
  (mapv #(apply vector %) (split-lines board)))

(defn coords [r0 rn c0 cn]
  (let [rows (range r0 (inc rn))
        cols (range c0 (inc cn))]
    (for [r rows c cols] (list r c))))

(def plus
  (zipmap (concat (range 1 10) [\* \space])
          (concat (range 2 11) [\* 1])))

(defn inc-in [grid pos]
  (if (get-in grid pos)
    (update-in grid pos plus)
    grid))

(defn mark-around [grid [r c :as pos]]
  (let [window (coords (dec r) (inc r) (dec c) (inc c))]
    (if (= \* (get-in grid pos))
      (reduce inc-in grid window)
      grid)))

(defn sweep [b]
  (let [each-pos (coords 0 (count b) 0 (count (first b)))]
    (reduce mark-around b each-pos)))

(defn draw [board]
  (->> board to-grid sweep (map join) (join "\n")))
