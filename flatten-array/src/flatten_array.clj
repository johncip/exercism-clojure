(ns flatten-array
  (:refer-clojure :exclude [flatten]))

;; original
(defn flatten [arr]
  (reduce
    (fn [a x]
      (cond
        (nil? x)  a
        (coll? x) (into a (flatten x))
        :else     (conj a x)))
    []
    arr))

;; :D
(defn flatten [arr]
  (let [res (atom [])]
    (clojure.walk/postwalk
      #(when (number? %) (swap! res conj %))
      arr)
    @res))
