(ns binary-search)

(defn middle [coll]
  (quot (count coll) 2))

(defn search-for [x coll]
  (let [mid (middle coll)
        mval (nth coll mid nil)]
    (when (empty? coll) (throw (Exception. "not found")))
    (condp apply [x mval]
      = mid
      < (search-for x (subvec coll 0 mid))
      > (+ 1 mid (search-for x (vec (drop (inc mid) coll)))))))
