(ns meetup
  (:import (java.time LocalDate DayOfWeek))
  (:require [clojure.string :as str]))

(defn days-in-month [y m]
  (let [start (LocalDate/of y m 1)
        end   (.plusMonths start 1)
        dates (.datesUntil start end)]
    (iterator-seq (.iterator dates))))

(defn day-of-week [date]
  (-> date .getDayOfWeek str/lower-case keyword))

(defn teenth? [date]
  (<= 13 (.getDayOfMonth date) 19))

(def pickers
  {:first  first
   :second second
   :third  #(nth % 2)
   :fourth #(nth % 3)
   :last   last
   :teenth #(first (filter teenth? %))})

(defn meetup-date [month year day which]
  (let [all-days (days-in-month year month)
        same-day? #(= (day-of-week %) day)
        pick (pickers which)]
    (pick (filter same-day? all-days))))

(defn meetup [month year day which]
  (let [date (meetup-date month year day which)]
    [year month (.getDayOfMonth date)]))
