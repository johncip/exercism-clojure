(ns crypto-square
  (:require [clojure.string :as str]))

(defn normalize-plaintext [s]
  (->> s str/lower-case (re-seq #"\w") str/join))

(defn square-size [s]
  (-> s normalize-plaintext count Math/sqrt Math/ceil int))

(defn strips [n coll]
  (map str/join (partition-all n coll)))

(defn plaintext-segments [s]
  (strips (square-size s) (normalize-plaintext s)))

(defn columns [s]
  (let [segs (plaintext-segments s)
        ncols (square-size s)
        nrows (count segs)]
    (strips nrows
      (for [c (range 0 ncols)
            r (range 0 nrows)]
        (-> segs (nth r) (nth c " "))))))

(defn ciphertext [s]
  (-> s columns str/join (str/replace " " "")))

(defn normalize-ciphertext [s]
  (->> (columns s)
       (map str/trim)
       (str/join " ")))
