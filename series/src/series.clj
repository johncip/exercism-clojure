(ns series)

(defn slices [string len]
  (cond
    (zero? len) [""]
    (> len (count string)) []
    :else (let [last-start (- (count string) len)
                starts (range 0 (inc last-start))
                window #(subs string %1 (+ %1 len))]
      (map window starts))))
