(ns prime-factors)

(def divisors
  (concat '(2) (iterate (partial + 2) 3)))

(defn of [num]
  (let [root (Math/sqrt num)]
    (loop [n num
           [d & ds :as divs] divisors
           factors []]
      (cond
        (= n 1)           factors
        (> d root)        (conj factors n) ; just a speedup
        (zero? (mod n d)) (recur (/ n d) divs (conj factors d))
        :else             (recur n ds factors)))))
