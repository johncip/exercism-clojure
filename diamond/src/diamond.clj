(ns diamond)

(defn mirror [xs]
  (concat xs (rest (reverse xs))))

(defn line [len i]
  (-> (vec (repeat len " "))
      (assoc (- len i 1) (char (+ 65 i)))
      mirror
      clojure.string/join))

(defn diamond [c]
  (let [len (inc (- (int c) (int \A)))]
    (mirror
      (map #(line len %) (range len)))))
