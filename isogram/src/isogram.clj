(ns isogram
  (:require [clojure.string :refer [lower-case]]))

(defn isogram? [s]
  (apply distinct? (remove #{\space \-} (lower-case s))))
