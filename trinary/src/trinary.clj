(ns trinary)

(def char->int {\0 0 \1 1 \2 2})

(defn join* [base]
  #(+ (* %1 base) %2))

(defn to-decimal [s]
  (if-not (every? char->int s) 0
    (reduce (join* 3) (map char->int s))))
