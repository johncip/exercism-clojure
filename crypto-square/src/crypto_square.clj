(ns crypto-square
  (:require [clojure.string :as str]))

(defn clean [s]
  (->> s str/lower-case (re-seq #"\w") str/join))
(def normalize-plaintext clean)

(defn square-size [s]
  (-> s clean count Math/sqrt Math/ceil int))

(defn segments [s]
  (let [n (square-size s)]
    (partition n n (repeat nil) s)))

(defn join-each [coll]
  (map str/join coll))

(def plaintext-segments
  (comp join-each segments clean))

(def rotated
  (let [rotate #(apply mapv vector %)]
    (comp join-each rotate segments)))

(defn ciphertext [s & delim]
  (str/join (first delim) (rotated (clean s))))

(defn normalize-ciphertext [s]
  (ciphertext s " "))
