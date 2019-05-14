(ns binary-search-tree)

(defn singleton [value]
  [nil value nil])

(def left first)
(def right last)
(def value second)

;; definitely not balanced...
(defn insert [v bst]
  (if (nil? bst)
    (singleton v)
    (condp apply [v (value bst)]
      <= (assoc bst 0 (insert v (left bst)))
       > (assoc bst 2 (insert v (right bst))))))

(defn from-list [[x & xs]]
  (reduce #(insert %2 %1) (singleton x) xs))

(def to-list (comp #(remove nil? %) flatten))
