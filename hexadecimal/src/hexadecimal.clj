(ns hexadecimal)

(def char->int
  (zipmap "0123456789abcdef" (range)))

(defn join* [base]
  #(+ (* %1 base) %2))

(defn hex-to-int [s]
  (if-not (every? char->int s) 0
    (reduce (join* 16) (map char->int s))))
