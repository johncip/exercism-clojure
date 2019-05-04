(ns octal)

(defn valid? [s]
  (re-matches #"[0-7]+" s))

(defn digit [c]
  (Character/digit c 10))

(defn to-decimal [s]
  (if-not (valid? s) 0
    (reduce #(+ (* %1 8) %2) (map digit s))))
