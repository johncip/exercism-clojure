(ns crypto-square
  (:require [clojure.string :as str]))

(defn normalize-plaintext [s]
  (->> s str/lower-case (re-seq #"\w") str/join))

(defn square-size [s]
  (-> s normalize-plaintext count Math/sqrt Math/ceil int))

(defn- segments [s]
  (let [n (square-size s)]
    (partition n n (repeat nil) s)))

(defn plaintext-segments [s]
  (map str/join (segments (normalize-plaintext s))))

(defn- rotated [s]
  (let [rotate #(apply mapv vector %)]
    (map str/join (rotate (segments s)))))

(defn ciphertext [s & delim]
  (str/join (first delim) (rotated (normalize-plaintext s))))

(defn normalize-ciphertext [s]
  (ciphertext s " "))
