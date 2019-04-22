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
  (let [final (last digits)]
    (conj
      (vec (map char->int (take 9 digits)))
      (if (= final \X) 10 (char->int final)))))

(defn weighted-sum [nums]
  (reduce
    (fn [sum pair]
      (+ (apply * pair) sum))
    0
    (map vector nums (range 1 11))))

(defn isbn? [isbn]
  (if-not (valid-format? isbn)
    false
    (-> isbn
        strip-hyphens
        to-nums
        weighted-sum
        (multiple-of? 11))))
