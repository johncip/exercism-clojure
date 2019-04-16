(ns rna-transcription)

(defn to-rna3 [dna]
  (do
    (assert (.contains [\G \C \T \A] dna))
    (condp = dna
      \G \C
      \C \G
      \T \A
      \A \U)))

(defn to-rna [dna]
  (str (reduce str (map to-rna3 dna))))
