(ns sum-of-multiples)

;; original
(defn some-multiple? [divs n]
  (some #(zero? (rem n %)) divs))

(defn sum-of-multiples [divs n]
  (->> (range 0 n)
       (filter (partial some-multiple? divs))
       (reduce +)))

;; better
(defn sum-of-multiples [divs n]
  (->> divs
       (mapcat (fn [step] (range 0 n step)))
       distinct
       (reduce +)))
