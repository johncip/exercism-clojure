(ns acronym
  (:require [clojure.string :as str]))

(defn acronym [s]
  (->> s
       (re-seq #"[A-Z][a-z]+|[a-z]+|[A-Z]+")
       (map first)
       str/join
       str/upper-case))
