(ns word-count
  (:require [clojure.string :as str]))


;; original
(defn freq [xs]
  (reduce #(merge-with + %1 {%2 1}) {} xs))

(defn normalize [word]
  (-> word
      (str/lower-case)
      (str/replace #"\W" "")))

(defn word-count-old [s]
  (as-> s t
      (str/split t #"\s+")
      (map normalize t)
      (remove str/blank? t)
      (freq t)))


;; improved
(def words #(re-seq #"\w+" %))

(defn word-count [s]
  (->> s str/lower-case words frequencies))
