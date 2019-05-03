(ns binary)

;; original
(defn to-decimal [s]
  (if-not (re-matches #"[01]+" s)
    0
    (->> (seq s)
         (map {\0 0 \1 1})
         reverse
         (map-indexed #(bit-shift-left %2 %1))
         (reduce +))))

;; minimal
(defn to-decimal [s]
  (if-not (every? #{\0 \1} s)
    0
    (->> (map {\0 0 \1 1} s)
         (reduce #(+ %2 (* 2 %1))))))
