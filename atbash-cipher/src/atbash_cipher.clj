(ns atbash-cipher
  (:require [clojure.string :as str]))

(def substitute
  (zipmap
    "abcdefghijklmnopqrstuvwxyz0123456789"
    "zyxwvutsrqponmlkjihgfedcba0123456789"))

(defn encode [s]
  (->> s
       str/lower-case
       (filter #(Character/isLetterOrDigit %))
       (map substitute)
       (partition 5 5 nil)
       (interpose \space)
       flatten
       str/join))
