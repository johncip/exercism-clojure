(ns crypto-square
  (:require [clojure.string :as str]))

(defn normalize-plaintext [s]
  (->> s str/lower-case (re-seq #"\w") str/join))

(defn square-size [s]
  (-> s normalize-plaintext count Math/sqrt Math/ceil int))

(defn- segments [n coll]
  (map str/join (partition-all n coll)))

(defn plaintext-segments [s]
  (segments (square-size s) (normalize-plaintext s)))

(defn- rotate [grid]
  (let [ncols (count (first grid))
        nrows (count grid)]
    (segments nrows
      (for [c (range 0 ncols)
            r (range 0 nrows)]
        (-> grid (nth r) (nth c " "))))))

(defn- columns [s]
  (map str/trim (rotate (plaintext-segments s))))

(def ciphertext
  (comp str/join columns))

(defn normalize-ciphertext [s]
  (str/join " " (columns s)))
