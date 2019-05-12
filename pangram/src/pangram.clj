(ns pangram
  (:require [clojure.string :as s]))

(defn pangram? [s]
  (= 26 (count (set (re-seq #"[a-z]" (s/lower-case s))))))
