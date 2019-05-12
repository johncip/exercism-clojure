(ns wordy
  (:require [clojure.string :as str]))

(def ops {"plus"          "+"
          "minus"         "-"
          "multiplied by" "*"
          "divided by"    "/"})

(def nums-and-ops
  (concat (keys ops) ["-?\\d"]))

(defn match-any [parts]
  (re-pattern (str "(" (str/join "|" parts) ")+")))

(defn valid? [s]
  (let [legal (concat nums-and-ops ["What is" "\\?" "\\s+"])]
    (re-matches (match-any legal) s)))

(defn apply-expr [[a op b & tail :as toks]]
  (if (nil? op)
    a
    (recur (conj tail (op a b)))))

(defn evaluate [s]
  (when-not (valid? s) (throw (IllegalArgumentException.)))
  (->> s
       (re-seq (match-any nums-and-ops))
       (map first)
       (replace ops)
       (map load-string)
       apply-expr))
