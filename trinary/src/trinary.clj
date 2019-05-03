(ns trinary)

(def char->int {\0 0 \1 1 \2 2})

(defn join* [base]
  #(+ %2 (* %1 base)))

(defn to-decimal [s]
  (if-not (every? char->int s) 0
    (reduce (join* 3) (map char->int s))))
