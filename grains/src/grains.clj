(ns grains)

(defn square [n]
  (.pow (biginteger 2) (dec n)))

(def total #(dec (square 65)))
