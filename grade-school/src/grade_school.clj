(ns grade-school)

(defn grade [school g]
  (school g []))

(defn add [school name g]
  (update school g #(conj (or % []) name)))

(defn sorted [school]
  (reduce-kv
    (fn [res k v] (assoc res k (sort v)))
    (sorted-map)
    school))

