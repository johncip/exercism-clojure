(ns atbash-cipher
  (:require [clojure.string :as str]))

(def substitute
  (zipmap
    "abcdefghijklmnopqrstuvwxyz0123456789"
    "zyxwvutsrqponmlkjihgfedcba0123456789"))

(defn encode [s]
  (->> (str/lower-case s)
       (keep substitute)
       (partition-all 5)
       (map str/join)
       (str/join " ")))
