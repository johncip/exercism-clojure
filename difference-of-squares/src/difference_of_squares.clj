(ns difference-of-squares)

(defn nums [n]
  (range 1 (inc n)))

(defn square [n]
  (* n n))

(defn square-of-sum [n]
  (square (reduce + (nums n))))

(defn sum-of-squares [n]
  (reduce + (map square (nums n))))

(defn difference [n]
  (- (square-of-sum n) (sum-of-squares n)))
