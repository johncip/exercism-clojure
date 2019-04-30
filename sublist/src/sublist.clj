(ns sublist)

(def starts-with?
  (memoize
    (fn [[y & ys] [x & xs]]
      (and (or (nil? x) (= x y))
           (or (nil? xs) (starts-with? ys xs))))))

(def sublist?
  (memoize
    (fn [a b]
      (let [[x & xs] a, [y & ys] b]
        (or
          (empty? a)
          (and (= x y) (starts-with? ys xs))
          (and (seq ys) (sublist? a ys)))))))

(defn classify [a b]
  (cond
    (and (sublist? a b) (sublist? b a)) :equal
    (sublist? a b) :sublist
    (sublist? b a) :superlist
    :else :unequal))
