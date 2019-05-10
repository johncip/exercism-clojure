(ns hexadecimal)


(def char->int {\0 0 \1 1 \2 2 \3 3 \4 4 \5 5 \6 6 \7 7 \8 8 \9 9
                \a 10 \b 11 \c 12 \d 13 \e 14 \f 15})

(defn join* [base]
  #(+ (* %1 base) %2))

(defn hex-to-int [s]
  (if-not (every? char->int s) 0
    (reduce (join* 16) (map char->int s))))
