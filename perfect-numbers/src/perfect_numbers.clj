(ns perfect-numbers)

;; original
(defn multiple? [n d]
  (zero? (rem n d)))

(defn aliquot-sum [n]
  (->> (range 1 (inc (quot n 2)))
       (filter (partial multiple? n))
       (reduce +)))

(defn classify [n]
  (if (neg? n) (throw (IllegalArgumentException.))
    (condp >= (compare (aliquot-sum n) n)
      -1 :deficient
       0 :perfect
       1 :abundant)))

;; better (condp with apply, stop at n/2, inline sum, less threading, use when)
(defn factors [n]
   (filter
     (partial multiple? n)
     (range 1 (inc (quot n 2)))))

(defn classify [n]
  (when (neg? n) (throw (IllegalArgumentException.)))
  (condp apply [(apply + (factors n)) n]
    < :deficient
    = :perfect
    > :abundant))
