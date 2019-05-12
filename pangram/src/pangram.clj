(ns pangram
  (:require [clojure.string :as str]))

(defn normalize [s]
  (-> s str/lower-case (str/replace #"[^a-z]" "")))

(defn pangram? [s]
  (= (seq "abcdefghijklmnopqrstuvwxyz")
     (keys (frequencies (normalize s)))))
