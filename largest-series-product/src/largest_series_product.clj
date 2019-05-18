(ns largest-series-product)

(defn largest-product [n ds]
  {:pre [(<= 0 n (count ds))
         (re-matches #"\d*" ds)]}
  (cond
    (zero? n) 1
    (empty? ds) 1
    :else (->> (map #(Character/digit % 10) ds)
               (partition n 1)
               (map #(apply * %))
               sort
               last)))
