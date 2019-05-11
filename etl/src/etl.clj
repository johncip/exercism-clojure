(ns etl
  (:require [clojure.string :refer [lower-case]]))

;; original version
(defn split-entry [[score words]]
  (map #(vector (lower-case %) score) words))

(defn transform [source]
  (into {} (mapcat split-entry source)))

;; zipmap version
(defn transform [mp]
  (let [repeat-key #(repeat (count (mp %)) %)
        keys'      (flatten (vals mp))
        vals'      (mapcat repeat-key (keys mp))]
    (zipmap (map lower-case keys') vals')))

;; for version
(defn transform [mp]
  (into {} (for [k (keys mp), v (mp k)]
             [(lower-case v) k])))
