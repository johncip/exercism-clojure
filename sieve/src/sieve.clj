(ns sieve)

(defn irange [a b & args]
  (apply range (concat [a (inc b)] args)))

(defn sieve [lim]
  (let [nums (atom (vec (irange 0 lim)))
        mark #(swap! nums assoc % nil)
        root (int (Math/sqrt lim))]
    (loop [i 2]
      (let [cur (nth @nums i nil)
            i' (inc i)]
        (cond
          (> i root)  (drop 2 (filter some? @nums))
          (nil? cur)  (recur i')
          :else       (do (doall (map mark (irange (* i i) lim i)))
                          (recur i')))))))
