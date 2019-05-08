(ns sieve)

(defn sieve [lim]
  (let [max (inc lim)
        nums (atom (vec (range 0 max)))
        mark #(swap! nums assoc % nil)]
    (loop [i 2]
      (let [cur (nth @nums i nil)]
        (cond
          (> i lim)  (drop 2 (filter some? @nums))
          (nil? cur) (recur (inc i))
          :else      (do (doall (map mark (range (* i 2) max i)))
                         (recur (inc i))))))))
