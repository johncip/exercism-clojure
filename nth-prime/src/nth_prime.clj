(ns nth-prime)

(defn sieve [lim]
  (if (< lim 2) '()
    (let [nums (atom (vec (range 0 (inc lim))))
          mark #(swap! nums assoc % nil)
          sqrt (Math/sqrt lim)]
      (loop [i 2]
        (let [cur (@nums i)
              doomed (range (* i i) (inc lim) i)
              i' (inc i)]
          (cond
            (> i sqrt)  (drop 2 (filter some? @nums))
            (nil? cur)  (recur i')
            :else       (do (doall (map mark doomed))
                            (recur i'))))))))

;; the nth prime is ~ n ln n
(defn guess [n]
  (+ 2 (int (* 4/3 n (Math/log n)))))

(defn nth-prime [n]
  (when-not (pos? n) (throw (IllegalArgumentException.)))
  (nth (sieve (guess n)) (dec n)))
