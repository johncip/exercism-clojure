(ns pascals-triangle)

(defn next-row [row]
  (conj (map (partial apply +')
             (partition-all 2 1 row))
        1))

(def triangle
  (do
    (def memo (atom {0 '()}))
    (map
      (fn [n]
        (let [prev (@memo (dec n))
              res (next-row prev)]
          (swap! memo assoc n res)
          res))
      (iterate inc 1))))

(defn row [n]
  (nth triangle (dec n)))

;; "how did I miss that" edition
(def triangle
  (iterate next-row '(1)))
