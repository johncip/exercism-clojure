(ns change)

(def search (memoize (fn [amt coins]
  (let [[c & cs] coins
        d (second coins)]
    (cond
      (zero? amt) []
      (empty? cs) (conj (search (- amt c) coins) c)
      (> c amt)   (search amt cs)
      :else       (min-key count
                    (conj (search (- amt c) coins) c)
                    (conj (search (- amt d) cs) d)))))))

(defn issue [amt coins]
  (if (and (not (zero? amt))
           (< amt (apply min coins)))
    (throw (IllegalArgumentException. "cannot change"))
    (search amt (sort > coins))))
