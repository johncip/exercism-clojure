(ns luhn)

(defn digits [s]
  (map #(Character/digit (first %) 10)
       (re-seq #"\d" s)))

(defn valid-format? [s]
  (and (re-matches #"[\d ]+" s)
       (> (count (digits s)) 1)))

(defn valid-digits? [s]
  (letfn [(dub9 [n] (- (* 2 n) (if (< n 5) 0 9)))]
    (->> s
         digits
         reverse
         (map-indexed #(if (even? %1) %2 (dub9 %2)))
         (reduce +)
         (#(mod % 10))
         zero?)))

(def valid?
  (every-pred valid-format? valid-digits?))
