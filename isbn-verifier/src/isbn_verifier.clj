(ns isbn-verifier
  (:require [clojure.string :as str]))

(defn valid? [isbn]
  (boolean (re-matches #"\d-?\d{3}-?\d{5}-?(\d|X)" isbn)))

(defn char->int [chr]
  (Character/digit chr 10))

(defn to-nums [digits]
  (let [final (last digits)]
    (conj (mapv char->int (take 9 digits))
          (if (= final \X) 10 (char->int final)))))

(defn weighted-sum [nums]
  (->> (reverse nums)
       (map-indexed #(* (inc %1) %2))
       (reduce +)))

(defn isbn? [isbn]
  (and (valid? isbn)
       (-> isbn
           (str/replace #"-" "")
           to-nums
           weighted-sum
           (mod 11)
           zero?)))
