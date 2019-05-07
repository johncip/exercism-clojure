(ns sum-of-multiples)

(defn some-multiple? [divs n]
  (some #(zero? (rem n %)) divs))

(defn sum-of-multiples [divs n]
  (loop [sum 0, n (dec n)]
    (cond
      (zero? n) sum
      (some-multiple? divs n) (recur (+ sum n) (dec n))
      :else (recur sum (dec n)))))
