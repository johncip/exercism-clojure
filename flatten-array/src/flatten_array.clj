(ns flatten-array)

(defn flatten [arr]
  (reduce
    (fn [a x]
      (cond
        (nil? x)  a
        (coll? x) (into a (flatten x))
        :else     (conj a x)))
    []
    arr))
