(ns change)

(def helper (memoize (fn [amt coins]
  (let [[c & cs] coins
        d (second coins)]
    (cond
      (zero? amt)    []
      (empty? coins) []
      (empty? cs)    (conj (helper (- amt c) coins) c)
      (> c amt)      (helper amt cs)
      :else          (min-key count
                       (conj (helper (- amt c) coins) c)
                       (conj (helper (- amt d) cs) d)))))))

(defn issue [amt coins]
  (let [err (IllegalArgumentException. "cannot change")]
    (cond
      (zero? amt) []
      (< amt (apply min coins)) (throw err)
      :else (helper amt (sort > coins)))))
