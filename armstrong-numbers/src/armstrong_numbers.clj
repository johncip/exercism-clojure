(ns armstrong-numbers)

(defn exp
  "Returns x raised to n."
  [x n]
  (reduce * (repeat n x)))

(defn num_digits [num]
  "Given a positive number, returns the number of digits."
  (loop [n num res 0]
    (if (zero? n)
      res
      (recur (quot n 10) (inc res)))))

(defn arm_sum [num]
  "Given a positive number, returns the sum of each digit raised to the number of digits."
  (loop [n num res 0]
    (if (zero? n)
      res
      (let [nd (num_digits num)]
        (recur (quot n 10) (+ res (exp (mod n 10) nd)))))))

(defn armstrong? [n]
  "Returns true if the given positive number is an armstrong number."
  (= (arm_sum n) n))
