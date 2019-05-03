(ns spiral-matrix)

(defn irange [a b] (range a (inc b)))
(defn irange- [a b] (range a (dec b) -1))

(defn coords [{xs :xs ys :ys size :size}]
  (for [x xs y ys] (+ x (* y size))))

(defn right [max depth]
  (coords {:xs (irange depth (- max depth))
           :ys (list depth)
           :size (inc max)}))

(defn down [max depth]
  (coords {:xs (list (- max depth))
           :ys (irange (inc depth) (- max depth))
           :size (inc max)}))

(defn left [max depth]
  (coords {:xs (irange- (- max depth 1) depth)
           :ys [(- max depth)]
           :size (inc max)}))

(defn top [max depth]
  (coords {:xs (list depth)
           :ys (irange- (- max depth 1) (inc depth))
           :size (inc max)}))

(defn make-strips [n level]
  (mapcat #(% (dec n) level) [right down left top]))

(defn spiral [size]
  (as-> (range 0 size) t
    (mapcat #(make-strips n %) t)
    (map vector t (iterate inc 1))
    (into (sorted-map) t)
    (vals t)
    (partition n t)))

(spiral 3)
