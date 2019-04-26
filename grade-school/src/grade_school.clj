(ns grade-school)

;; original
(defn grade [school gr]
  (school gr []))

(defn add [school name gr]
  (let [students (grade school gr)
        updated (conj students name)]
    (assoc school gr updated)))

(defn sorted [school]
  (reduce-kv
    (fn [res k v] (assoc res k (sort v)))
    (sorted-map)
    school))

;; alternative add (shorter but weird)
(defn add [school name gr]
  (update school gr #(conj (or % []) name)))
