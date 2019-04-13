(ns armstrong-numbers)

;; returns x^n
;; (could also have used Math.exp)
(defn exp [x n]
  (reduce * (repeat n x)))

;; number of digits. only works for num > 0
;; (could also wrap & floor Math.log)
(defn num_digits [num]
  (loop [n num res 0]
    (if (zero? n)
      res
      (recur (quot n 10) (inc res)))))

;; sum of each digit raised to the number of digits
;; (could also make a list of digits & reduce)
(defn arm_sum [num]
  (loop [n num res 0]
    (if (zero? n)
      res
      (let [nd (num_digits num)]
        (recur (quot n 10) (+ res (exp (mod n 10) nd)))))))

;; true if n is to sum of each digit raised etc.
(defn armstrong? [n]
  (= (arm_sum n) n))
