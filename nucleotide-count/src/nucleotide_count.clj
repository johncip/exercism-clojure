(ns nucleotide-count)

(defn nucleotide-counts [strand]
  (merge
    {\A 0 \C 0 \G 0 \T 0}
    (frequencies strand)))

(defn count [tide strand]
  (do (assert (contains? #{\A \C \G \T} tide))
      (get (nucleotide-counts strand) tide 0)))
