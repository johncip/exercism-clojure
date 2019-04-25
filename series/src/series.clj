(ns series
  (:require [clojure.string :as str]))

;; original
(defn slices [string len]
  (cond
    (zero? len) [""]
    (> len (count string)) []
    :else
    (let [last-start (- (count string) len)
          starts (range 0 (inc last-start))
          window #(subs string %1 (+ %1 len))]
      (map window starts))))


;; better (use partition)
(defn slices [s len]
  (cond
    (zero? len) [""]
    (> len (count s)) []
    :else (->> s (partition len 1) (map str/join))))
