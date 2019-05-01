(ns triangle
  (:refer-clojure :exclude [type]))

(defn type [a b c]
  (let [sorted (sort [a b c])
        groups (partition-by identity sorted)
        [s1 s2 s3] sorted]
    (case (count groups)
      1 :equilateral
      2 (if (<= (* s1 2) s3) :illogical :isosceles)
      3 :scalene)))
