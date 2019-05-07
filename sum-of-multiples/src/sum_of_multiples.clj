(ns sum-of-multiples)

(defn some-multiple? [divs n]
  (some #(zero? (rem n %)) divs))

(defn sum-of-multiples [divs n]
  (->> (range 0 n)
       (filter (partial some-multiple? divs))
       (reduce +)))
