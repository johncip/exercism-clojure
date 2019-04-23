(ns nucleotide-count)

;; original
(defn nucleotide-counts [strand]
  (merge
    {\A 0 \C 0 \G 0 \T 0}
    (frequencies strand)))

(defn count [tide strand]
  (do (assert (contains? #{\A \C \G \T} tide))
      (get (nucleotide-counts strand) tide 0)))


;; better (into > merge, real precondition, don't need get...0)
(defn nucleotide-counts [strand]
  (into {\A 0, \T 0, \C 0, \G 0} (frequencies strand)))

(defn count [tide strand]
  {:pre [(#{\G \A \C \T} tide)]}
  ((nucleotide-counts strand) tide))
