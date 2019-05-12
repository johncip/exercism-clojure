(ns isogram
  (:require [clojure.string :as str]))

(defn isogram? [word]
  (->> word
       str/lower-case
       (remove #{\space \-})
       (apply distinct?)))
