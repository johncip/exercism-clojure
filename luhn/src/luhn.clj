(ns luhn)

(defn digits [s]
  (map #(Character/digit (first %) 10)
       (re-seq #"\d" s)))

(defn valid-format? [s]
  (and (re-matches #"[\d ]+" s)
       (>= (count (digits s)) 2)))

(defn double9 [n]
  (if (= n 9) n (mod (* n 2) 9)))

(defn double-every-other [ds]
  (map-indexed
    #(if (even? %1) %2 (double9 %2))
    ds))

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
