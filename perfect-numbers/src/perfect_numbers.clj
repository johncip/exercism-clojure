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

;; better (use condp with apply, don't go past n/2 + 1)
(defn aliquot-sum [n]
  (->> (range 1 (inc (quot n 2)))
       (filter (partial multiple? n))
       (reduce +)))

(defn classify [n]
  (if (neg? n) (throw (IllegalArgumentException.))
    (condp apply [(aliquot-sum n) n]
      < :deficient
      = :perfect
      > :abundant)))
