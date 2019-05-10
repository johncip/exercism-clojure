(ns accumulate)

(defn accumulate [f xs]
  (loop [res [] xs xs]
    (if-not (seq xs)
      res
      (let [[x & xs] xs]
        (recur (conj res (f x)) xs)))))
