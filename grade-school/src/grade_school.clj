(ns grade-school)

(defn grade [school grade]
  (get school grade []))

(defn add [school name gr]
  (let [students (grade school gr)]
    (assoc school gr (conj students name))))

(defn sorted [school]
  (reduce-kv
    (fn [res k v] (assoc res k (sort v)))
    (sorted-map)
    school))
