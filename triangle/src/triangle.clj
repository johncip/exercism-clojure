(ns triangle
  (:refer-clojure :exclude [type]))

(defn type [a b c]
  (let [[s1 s2 s3 :as sorted] (sort [a b c])
        groups (partition-by identity sorted)]
    (case (count groups)
      1 :equilateral
      2 (if (<= (+ s1 s2) s3) :illogical :isosceles)
      3 :scalene)))
