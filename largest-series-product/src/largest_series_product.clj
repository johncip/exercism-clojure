(ns largest-series-product)

(def digit #(Character/digit % 10))

(defn largest-product [n ds]
  {:pre [(<= 0 n (count ds))
         (re-matches #"\d*" ds)]}
  (cond
    (zero? n) 1
    (empty? ds) 1
    :else (->> (map digit ds)
               (partition n 1)
               (map #(apply * %))
               sort
               last)))
