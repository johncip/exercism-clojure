(ns reverse-string)

(defn reverse-string [s]
  "Given a string, returns one with the characters in the opposite order."
  (reduce #(str %2 %1) "" s))
