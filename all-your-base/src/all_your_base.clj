(ns all-your-base)

(defn valid-conversion? [base1 digits base2]
  (and
    (seq digits)
    (not-any? neg? digits)
    (every? #(> % 1) [base1 base2])
    (every? #(< % base1) digits)))

(defn to-decimal [base digits]
  (reduce #(+ (* %1 base) %2) digits))

(defn to-digits [base n]
  (if (< n base)
    [n]
    (conj (to-digits base (quot n base)) (mod n base))))

(defn convert [base1 digits base2]
  (if (valid-conversion? base1 digits base2)
    (->> digits
         (to-decimal base1)
         (to-digits base2))))
