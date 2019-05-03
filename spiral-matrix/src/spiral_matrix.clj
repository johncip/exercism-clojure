(ns spiral-matrix)

(defn coords [{xs :xs ys :ys n :n}]
  (for [x xs y ys] (+ x (* y n))))

(defn right [n d]
  (coords {:xs (range d (- n d))
           :ys (list d)
           :n n}))

(defn down [n d]
  (coords {:xs (list (- (dec n) d))
           :ys (range (inc d) (- n d))
           :n n}))

(defn left [n d]
  (coords {:xs (range (- n d 2) (dec d) -1)
           :ys [(- (dec n) d)]
           :n n}))

(defn top [n d]
  (coords {:xs (list d)
           :ys (range (- n d 2) d -1)
           :n n}))

(defn make-strips [n d]
  (mapcat #(% n d) [right down left top]))

(defn spiral [n]
  (as-> (range 0 n) t
    (mapcat #(make-strips n %) t)
    (map vector t (iterate inc 1))
    (into (sorted-map) t)
    (vals t)
    (partition n t)))
