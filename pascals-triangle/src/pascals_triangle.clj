(ns pascals-triangle)

(defn nth-val [prev n]
  (+' (nth prev (dec n)) (nth prev n)))

(defn middle [prev]
  (map (partial nth-val prev) (range 1 (count prev))))

(def triangle
  (do
    (def memo (atom {}))
    (map
      (fn [n]
        (let [m (dec n)
              prev (@memo m)
              res (if (= 1 n) [1]
                    (flatten [1 (middle prev) 1]))]
          (swap! memo assoc n res)
          res))
      (iterate inc 1))))

(defn row [n]
  (first (drop (dec n) triangle)))
