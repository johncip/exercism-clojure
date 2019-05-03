(ns trinary)

(def char->int {\0 0 \1 1 \2 2})

(defn to-decimal [s]
  (if-not (every? (set (keys char->int)) s)
    0
    (->> (map char->int s)
         (reduce (fn [acc d] (+ d (* 3 acc)))))))
