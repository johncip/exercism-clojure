(ns spiral-matrix)

(defn positions [{xs :xs ys :ys n :n}]
  (for [x xs y ys] (+ x (* y n))))

(defn right [n d]
  (positions {:xs (range d (- n d))
              :ys (list d)
              :n n}))

(defn down [n d]
  (positions {:xs (list (- (dec n) d))
              :ys (range (inc d) (- n d))
              :n n}))

(defn left [n d]
  (positions {:xs (range (- n d 2) (dec d) -1)
              :ys [(- (dec n) d)]
              :n n}))

(defn top [n d]
  (positions {:xs (list d)
              :ys (range (- n d 2) d -1)
              :n n}))

(defn ring [n depth]
  (mapcat #(% n depth) [right down left top]))

;; roughly: list the "coordinates" in spiral order,
;; pair each with its position in the list,
;; then sort & return the re-ordered list positions
(defn spiral [n]
  (as-> (range 0 n) t
    (mapcat #(ring n %) t)
    (map vector t (iterate inc 1))
    (into (sorted-map) t)
    (vals t)
    (partition n t)))
