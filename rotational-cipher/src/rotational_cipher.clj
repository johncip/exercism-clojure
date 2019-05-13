(ns rotational-cipher
  (:require [clojure.string :as str]))

(defn cipher [n alphabet]
  (zipmap (take 26 (cycle alphabet))
          (take 26 (drop (mod n 26) (cycle alphabet)))))

(def lowers "abcdefghijklmnopqrstuvwxyz")
(def uppers (str/upper-case lowers))

(defn rotate-letter [n c]
  (cond
    (not (Character/isLetter c)) c
    (Character/isUpperCase c)    ((cipher n uppers) c)
    :else                        ((cipher n lowers) c)))

(defn rotate [s n]
  (apply str (map (partial rotate-letter n) s)))
