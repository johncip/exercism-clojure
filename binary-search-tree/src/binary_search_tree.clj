(ns binary-search-tree)

(defn singleton [value]
  {:value value})

(doseq [k [:left :right :value]]
  (intern *ns* (symbol k) k))

;; definitely not self-balancing
(defn insert [v bst]
  (if (nil? bst)
    (singleton v)
    (condp apply [v (value bst)]
      <= (assoc bst :left (insert v (left bst)))
       > (assoc bst :right (insert v (right bst))))))

;; there must be a better way
(defn to-list [bst]
  (let [l (left bst)
        r (right bst)
        v (value bst)]
    (cond
      (not (or l r)) [v]
      (not l)        (concat [v] (to-list r))
      (not r)        (concat (to-list l) [v])
      :else          (concat (to-list l) [v] (to-list r)))))

(defn from-list [ls]
  (reduce #(insert %2 %1) (singleton (first ls)) (rest ls)))

;; (from-list (shuffle (range 1 6)))
;; (to-list (from-list (shuffle (range 1 6))))
