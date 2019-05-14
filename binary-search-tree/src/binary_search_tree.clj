(ns binary-search-tree)

(defn singleton [value]
  [nil value nil])

(doseq [[k v] {'left first 'value second 'right last}]
  (intern *ns* k v))

;; definitely not balanced...
(defn insert [v bst]
  (if (nil? bst)
    (singleton v)
    (condp apply [v (value bst)]
      <= (assoc bst 0 (insert v (left bst)))
       > (assoc bst 2 (insert v (right bst))))))

(defn from-list [xs]
  (reduce #(insert %2 %1) nil xs))

(defn to-list [bst]
  (->> bst flatten (remove nil?)))
