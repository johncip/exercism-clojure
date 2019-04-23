(ns anagram
 (:require [clojure.string :as str]))

(def msort (memoize sort))

(defn anagram? [a b]
  (and (not= a b)
       (= (msort a) (msort b))))

(defn anagrams-for [word candidates]
  (->> candidates
       (map str/lower-case)
       (filter (partial anagram? word))))
