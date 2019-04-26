(ns etl
  (:require [clojure.string :as str]))

(defn split-entry [[score words]]
  (map #(vector (str/lower-case %) score) words))

(defn transform [source]
  (into {} (mapcat split-entry source)))
