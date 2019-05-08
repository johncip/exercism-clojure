(ns luhn)

(defn digits [s]
  (map read-string (re-seq #"\d" s)))

(defn valid-format? [s]
  (and (re-matches #"[\d ]+" s)
       (> (count (digits s)) 1)))

(defn double-every-other [ds]
  (map-indexed
    (fn [i d] (cond (even? i) d
                    (< d 5)   (* 2 d)
                    :else     (- (* 2 d) 9)))
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
