(ns proverb
  (:require [clojure.string :as str]))

(defn line [[thing other-thing]]
  (str "For want of a " thing " the "
       other-thing " was lost."))

(defn ending [thing]
  (str "And all for the want of a " thing "."))

(defn recite [stuff]
  (let [pairs (partition 2 1 stuff)
        lines (mapv line pairs)
        last-line (ending (first stuff))]
    (if (empty? stuff)
      ""
      (str/join "\n" (conj lines last-line)))))
