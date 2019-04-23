(ns phone-number
  (:require [clojure.string :as str]))

(def pattern
  (re-pattern
    (str
      "(?:\\+?1 ?)?" ; country-code?
      "\\(?"         ; lparen?
      "(\\d{3})"     ; area code
      "\\)?"         ; rparen?
      "[\\. -]?"     ; separator?
      "(\\d{3})"     ; prefix
      "[\\. -]?"     ; separator?
      "(\\d{4})"     ; line number
      )))

(defn segments [num-string]
  (drop 1 (re-matches pattern num-string)))

(defn number [num-string]
  (let [segs (segments num-string)]
    (if (seq segs)
      (apply str segs)
      "0000000000")))

(defn area-code [num-string]
  (subs (number num-string) 0 3))

(defn pretty-print [num-string]
  (->> num-string
       number
       segments
       (apply format "(%s) %s-%s")))
