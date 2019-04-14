(ns bob)
(use '[clojure.string :only (blank? upper-case)])

(defn all-upper? [s] (= s (upper-case s)))
(defn question? [s] (= (last s) \?))
(def has-letters? (partial re-find #"[a-zA-Z]"))
(def yelling? (every-pred has-letters? all-upper?))
(def loud-question? (every-pred yelling? question?))

(defn response-for [s]
  (cond
    (blank? s)         "Fine. Be that way!"
    (loud-question? s) "Calm down, I know what I'm doing!"
    (yelling? s)       "Whoa, chill out!"
    (question? s)      "Sure."
    :else              "Whatever."))
