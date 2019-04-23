(ns collatz-conjecture)

(defn collatz [num]
  {:pre [(pos? num)]}
  (loop [ops 0, n num]
    (cond
      (= 1 n)   ops
      (even? n) (recur (inc ops) (quot n 2))
      (odd? n)  (recur (inc ops) (inc (* 3 n))))))
