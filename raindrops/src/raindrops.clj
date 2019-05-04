(ns raindrops)

(defn convert [n]
  (let [dict  {3 "Pling" 5 "Plang" 7 "Plong"}
        nums  (filter #(zero? (mod n %)) (keys dict))
        drops (map dict nums)]
    (if (seq nums)
      (apply str drops)
      (str n))))
