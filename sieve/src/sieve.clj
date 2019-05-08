(ns sieve)

(defn irange [a b & args]
  (apply range (concat [a (inc b)] args)))

(defn sieve [lim]
  (let [nums (atom (vec (irange 0 lim)))
        mark #(swap! nums assoc % nil)
        sqrt (Math/sqrt lim)]
    (loop [i 2]
      (let [cur (nth @nums i nil)
            doomed (irange (* i i) lim i)
            i' (inc i)]
        (cond
          (> i sqrt)  (drop 2 (filter some? @nums))
          (nil? cur)  (recur i')
          :else       (do (doall (map mark doomed))
                          (recur i')))))))
