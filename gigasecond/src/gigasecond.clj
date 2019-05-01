(ns gigasecond)

(def gsec-days
  (/ (Math/pow 10 9) (* 24 60 60)))

(defn date->vec [d]
  [(.getYear d)
   (.getMonthValue d)
   (.getDayOfMonth d)])

(defn from [y m d]
  (-> (java.time.LocalDate/of y m d)
      (.plusDays gsec-days)
      date->vec))

;; better
(defn from [y m d]
  (-> (java.time.LocalDateTime/of y m d 0 0 0 0)
      (.plusSeconds 1e9)
      date->vec))
