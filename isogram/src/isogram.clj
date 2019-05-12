(ns isogram
  (:require [clojure.string :as str]))

(defn isogram? [word]
  (apply distinct? (remove #{\space \-} (str/lower-case word))))
