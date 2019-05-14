(ns pig-latin
  (:require [clojure.string :as str]))

(defn boundary-matches [word]
  (re-matches
    (re-pattern
      (str/join "|"
        ["([aeiou].*)"
         "(xr.*)"
         "(yt.*)"
         "([bcdfghjklmnpqrstvwxz])(y)"
         "([bcdfghjklmnprstvwxyz]*qu)(.*)"
         "([bcdfghjklmnpqrstvwxyz]+)(.*)"
         ])) word))

(defn split [word]
  (drop 1 (filter some? (boundary-matches word))))

(defn say [word]
  (-> (split word)
      reverse
      str/join
      (str "ay")))

(defn translate [words]
  (->> (str/split words #"\s")
       (map say)
       (str/join " ")))
