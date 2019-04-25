(ns say
  (:require [clojure.string :as str]))

(declare split-thousands say-small with-suffixes join-present)


(defn number [num]
  (when-not (<= 0 num 999999999999) (throw (IllegalArgumentException.)))
  (if (zero? num) "zero"
    (->> num
         split-thousands
         (map say-small)
         with-suffixes
         (join-present " "))))


(defn split-thousands [num]
  (loop [n num, res ()]
    (if (zero? n) res
      (recur (quot n 1000) (conj res (mod n 1000))))))


(defn say-small [n]
  (let [ones {0 ""     1 "one" 2 "two"   3 "three" 4 "four"
              5 "five" 6 "six" 7 "seven" 8 "eight" 9 "nine"}

        tens {0 ""      1 "onety" 2 "twenty"  3 "thirty" 4 "forty"
              5 "fifty" 6 "sixty" 7 "seventy" 8 "eighty" 9 "ninety"}

        teens {10 "ten"      11 "eleven"    12 "twelve"
               13 "thirteen" 14 "fourteen"  15 "fifteen"
               16 "sixteen"  17 "seventeen" 18 "eighteen"
               19 "nineteen"}]
  (condp >= n
    9 (ones n)
    19 (teens n)
    99 (join-present "-" [(tens (quot n 10)) (ones (mod n 10))])
    999 (join-present " " [(say-small (quot n 100)) "hundred" (say-small (mod n 100))]))))


(defn with-suffixes [groups]
  (let [suffixes ["" "thousand" "million" "billion"]]
    (->> groups
         reverse
         (map-indexed #(join-present " " [%2 (suffixes %1)]))
         reverse)))


(defn join-present [delim coll]
  (str/join delim (take-while seq coll)))
