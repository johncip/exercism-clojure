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

(defn to-plots [rows]
  (->> (map #(partition 2 %) rows)
       (apply map concat)))

(defn garden
  ([s] (garden s default-kids))

  ([s names]
   (let [kids (mapv normalize (sort names))]
     (->> (str/split s #"\n")
          to-plots
          (map #(map plants %))
          (zipmap kids)))))
