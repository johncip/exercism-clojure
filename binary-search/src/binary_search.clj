(ns binary-search)

(defn middle [coll]
  (quot (count coll) 2))

(defn search-for [x coll]
  (when (empty? coll) (throw (Exception. "not found")))

  (let [m (middle coll)
        n (inc m)
        mval (nth coll m nil)]
    (condp apply [x mval]
      = m
      < (->> coll (take m) (search-for x))
      > (->> coll (drop n) (search-for x) (+ n)))))
