(ns kindergarten-garden
  (:require [clojure.string :as str]))

(def default-kids
  (mapv str
    '(alice bob charlie david
      eve fred ginny harriet
      ileana joseph kincaid larry)))

(def plants
  {\C :clover   \G :grass
   \R :radishes \V :violets})

(def normalize
  (comp keyword str/lower-case))

(defn collate [rows]
  (->> (map #(partition 2 %) rows)
       (apply map vector)
       flatten))

(defn garden
  ([s]
   (garden s default-kids))

  ([s names]
   (let [kids (mapv normalize (sort names))]
     (->> (str/split s #"\n")
          collate
          (map plants)
          (partition 4)
          (map-indexed #(vector (kids %1) %2))
          (into {})))))
