(ns flatten-array
  (:require [clojure.walk :as walk]))

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
    (walk/postwalk
      #(when (number? %) (swap! res conj %))
      arr)
    @res))
