(ns largest-series-product)

(defn largest-product [n ds]
  {:pre [(<= 0 n (count ds))
         (re-matches #"\d*" ds)]}
  (if (zero? n) 1
    (->> (map #(Character/digit % 10) ds)
         (partition n 1)
         (map #(apply * %))
         sort
         last)))
