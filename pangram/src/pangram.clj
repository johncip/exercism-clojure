(ns pangram
  (:require [clojure.string :as s]))

(defn normalize [s]
  (-> s s/lower-case (s/replace #"[^a-z]" "")))

(defn pangram? [s]
  (= (count (frequencies (normalize s))) 26))
