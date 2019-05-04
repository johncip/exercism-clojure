(ns strain)

;; 😎
(defn retain [pred coll]
  (mapcat #(if (pred %1) [%1] []) coll))

(defn discard [pred coll]
  (retain (complement pred) coll))
