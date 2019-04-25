(ns armstrong-numbers)

;; original
(defn exp [x n]
  (reduce * (repeat n x)))

(defn num-digits [num]
  (loop [n num res 0]
    (if (zero? n)
      res
      (recur (quot n 10) (inc res)))))

(defn armstrong-sum [num]
  (loop [n num res 0]
    (if (zero? n)
      res
      (let [nd (num-digits num)]
        (recur (quot n 10) (+ res (exp (mod n 10) nd)))))))


;; better (simpler digit splitting, map & reduce instead of loop)
(defn digits [n]
  (map #(Character/digit % 10) (str n)))

(defn exp [b x]
  (-> b biginteger (.pow x)))

(defn armstrong-sum [num]
  (let [digs (digits num)
        raise #(exp %1 (count digs))]
    (reduce + (map raise digs))))

(defn armstrong? [n]
  (= (armstrong-sum n) n))
