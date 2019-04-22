(ns isbn-verifier)

(defn valid-format? [isbn]
  (re-matches #"\d-?\d{3}-?\d{5}-?(\d|X)" isbn))

(defn strip-hyphens [s]
  (clojure.string/replace s #"-" ""))

(defn char->int [chr]
  (Character/digit chr 10))

(defn multiple-of? [num base]
  (zero? (mod num base)))

(defn to-nums [digits]
  "Given a string containing nine digits (0-9), plus a tenth which can
  be a digit or the letter X (for 10), returns a sequence of numbers."
  (let [final (last digits)]
    (conj
      (vec (map char->int (take 9 digits)))
      (if (= final \X) 10 (char->int final)))))

(defn weighted-sum [nums]
  "Sums the numbers, multiplying each by its position in reverse order:
  = a * 10 + b * 9 + ..."
  (reduce
    (fn [sum pair]
      (+ (apply * pair) sum))
    0
    (map vector nums (range 1 11))))

(defn isbn? [isbn]
  "Returns true if the string is a valid ISBN-10."
  (if-not (valid-format? isbn)
    false
    (-> isbn
        strip-hyphens
        to-nums
        weighted-sum
        (multiple-of? 11))))
