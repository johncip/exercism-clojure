(ns roman-numerals)

(def arabic->roman
  (sorted-map-by
    (comp - compare)
    1000 "M" 900 "CM" 500 "D" 400 "CD"
     100 "C"  90 "XC"  50 "L"  40 "XL"
      10 "X"   9 "IX"   5 "V"   4 "IV"
       1 "I"   0 ""))

(defn numerals [num]
  (loop [res "" n num]
    (let [as (keys arabic->roman)
          a (first (filter (partial >= n) as))
          r (arabic->roman a)]
      (if (zero? n) res
        (recur (str res r) (- n a))))))
