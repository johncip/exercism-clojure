(ns luhn)

(defn digits [s]
  (map #(Character/digit (first %) 10)
       (re-seq #"\d" s)))

(defn valid-format? [s]
  (and (re-matches #"[\d ]+" s)
       (> (count (digits s)) 1)))

(defn double-every-other [ds]
  (letfn [(dub [n] (- (* 2 n) (if (< n 5) 0 9)))]
    (map-indexed
      (fn [i d] (if (even? i) d (dub d)))
      ds)))

(defn valid-digits? [s]
  (->> s
       digits
       reverse
       double-every-other
       (reduce +)
       (#(mod % 10))
       zero?))

(def valid?
  (every-pred valid-format? valid-digits?))
