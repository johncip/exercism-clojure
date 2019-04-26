(ns perfect-numbers)

(defn multiple? [n d]
  (zero? (rem n d)))

(defn aliquot-sum [n]
  (->> (range 1 n)
       (filter (partial multiple? n))
       (reduce +)))

(defn classify [n]
  (if (neg? n) (throw (IllegalArgumentException.))
    (condp >= (compare (aliquot-sum n) n)
      -1 :deficient
       0 :perfect
       1 :abundant)))
