(ns word-count
  (:require [clojure.string :as str]))

(defn freq [xs]
  (reduce #(merge-with + %1 {%2 1}) {} xs))

(defn normalize [word]
  (-> word
      (str/lower-case)
      (str/replace #"\W" "")))

(defn word-count [s]
  (as-> s t
      (str/split t #"\s+")
      (map normalize t)
      (remove str/blank? t)
      (freq t)))
