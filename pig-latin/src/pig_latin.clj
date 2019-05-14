(ns pig-latin
  (:require [clojure.string :as str]))

(def patterns
  [#"(xr.*)"
   #"(yt.*)"
   #"([aeiou].*)"
   #"([bcdfghjklmnpqrstvwxz]+)(y.*)"
   #"([bcdfghjklmnprstvwxyz]*qu)(.*)"
   #"([bcdfghjklmnpqrstvwxyz]+)(.*)"])

(defn parts [word]
  (->> (some #(re-matches % word) patterns)
       (remove nil?)
       rest))

(defn say [word]
  (str/join (concat (reverse (parts word)) ["ay"])))

(defn translate [phrase]
  (->> (str/split phrase #"\s")
       (map say)
       (str/join " ")))
