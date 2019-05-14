(ns pig-latin
  (:require [clojure.string :as str]))

(def patterns
  [#"((?:[aeiou]|xr|yt).*)"
   #"([bcdfghjklmnpqrstvwxz]+)(y.*)"
   #"([bcdfghjklmnprstvwxyz]*qu)(.*)"
   #"([^aeiou]+)(.*)"])

(defn parts [word]
  (rest (some #(re-matches % word) patterns)))

(defn say [word]
  (str/join (concat (reverse (parts word)) ["ay"])))

(defn translate [phrase]
  (->> (str/split phrase #"\s")
       (map say)
       (str/join " ")))

