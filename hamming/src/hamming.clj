(ns hamming)

;; original
(def different?
  (partial apply not=))

(def bool->int
  {true 1, false 0})

(defn count= [a b]
  (= (count a) (count b)))

(defn distance [a b]
  (if
    (not (count= a b)) nil
    (->> [a b]
         (apply map list)
         (map (comp bool->int different?))
         (reduce +))))


;; better -- use filter & count
(defn distance [a b]
  (when (count= a b)
    (->> [a b]
         (apply map not=)
         (filter true?)
         (count))))
