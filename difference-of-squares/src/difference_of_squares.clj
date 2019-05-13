(ns difference-of-squares)

(defn nums [n]
  (range 1 (inc n)))

(defn square [n]
  (* n n))

(def sum
  (partial reduce +))

(def square-of-sum
  (comp square sum nums))

(def sum-of-squares
  (comp sum (partial map square) nums))

(defn difference [n]
  (- (square-of-sum n) (sum-of-squares n)))
