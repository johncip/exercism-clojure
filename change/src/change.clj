(ns change)

(def search (memoize (fn [amt coins]
  (let [[c & cs] coins
        d (second coins)]
    (cond
      (zero? amt)    []
      (empty? coins) []
      (empty? cs)    (conj (search (- amt c) coins) c)
      (> c amt)      (search amt cs)
      :else          (min-key count
                       (conj (search (- amt c) coins) c)
                       (conj (search (- amt d) cs) d)))))))

(defn issue [amt coins]
  (let [err (IllegalArgumentException. "cannot change")]
    (cond
      (zero? amt) []
      (< amt (apply min coins)) (throw err)
      :else (search amt (sort > coins)))))
