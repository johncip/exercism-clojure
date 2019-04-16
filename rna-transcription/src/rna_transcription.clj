(ns rna-transcription)

(defn other [base]
  (do
    (assert (.contains [\G \C \T \A] base))
    (condp = base
      \G \C
      \C \G
      \T \A
      \A \U)))

(defn to-rna [dna]
  (apply str (map other dna)))
