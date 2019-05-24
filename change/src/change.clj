(ns change)

(defn smaller [a b]
  (if (< (count a) (count b)) a b))

(def helper
  (memoize
    (fn [amt coins]
      (let [[c & cs] coins
            c2 (second coins)]
        (cond
          (zero? amt) []
          (empty? coins) []
          (> c amt) (helper amt cs)
          (empty? cs) (conj (helper (- amt c) coins) c)
          :else (smaller
                  (conj (helper (- amt c) coins) c)
                  (conj (helper (- amt c2) cs) c2)))))))

(defn issue [amt coinset]
  (let [err (IllegalArgumentException. "cannot change")]
    (cond
      (zero? amt) []
      (< amt (apply min coinset)) (throw err)
      :else (helper amt (sort > (vec coinset))))))
