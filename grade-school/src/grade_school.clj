(ns grade-school)

;; original
(defn grade [school g]
  (school g []))

(defn add [school name g]
  (let [students (grade school g)
        updated (conj students name)]
    (assoc school g updated)))

(defn sorted [school]
  (reduce-kv
    (fn [res k v] (assoc res k (sort v)))
    (sorted-map)
    school))

;; alternative add (shorter but weird)
(defn add [school name g]
  (update school g #(conj (or % []) name)))
