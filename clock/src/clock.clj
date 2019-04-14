(ns clock)

(defn clock [hours minutes]
  (mod (+ (* hours 60) minutes) (* 24 60)))

(defn clock->string [clock]
  (format "%02d:%02d" (quot clock 60) (mod clock 60)))

(defn add-time [clk time]
  (clock 0 (+ clk time)))
