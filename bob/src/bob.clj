(ns bob)
(use '[clojure.string :only (trim upper-case)])

(defn has-letters? [s] (re-find #"[A-Z]" s))
(defn all-upper? [s] (= s (upper-case s)))
(defn silence? [s] (empty? (trim s)))
(defn question? [s] (= (last s) \?))
(def yelling? (every-pred has-letters? all-upper?))
(def yell-question? (every-pred yelling? question?))

(defn response-for [s]
  (cond
    (silence? s)       "Fine. Be that way!"
    (yell-question? s) "Calm down, I know what I'm doing!"
    (yelling? s)       "Whoa, chill out!"
    (question? s)      "Sure."
    :else              "Whatever."))
