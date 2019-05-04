(ns atbash-cipher
  (:require [clojure.string :as str]))

(def substitute
  (zipmap
    "abcdefghijklmnopqrstuvwxyz0123456789"
    "zyxwvutsrqponmlkjihgfedcba0123456789"))

(defn encode [s]
  (->> s
       str/lower-case
       (keep substitute)
       (partition-all 5)
       (interpose \space)
       flatten
       str/join))

(atbash-cipher/encode "Testing, 1 2 3, testing.")
