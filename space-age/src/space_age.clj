(ns space-age)

(def periods
  {:earth   1         :mercury 0.240846
   :venus   0.615197  :mars    1.880815
   :jupiter 11.86261  :saturn  29.44749
   :uranus  84.01684  :neptune 164.7913})

(periods :mercury)

(doseq [[planet period] (seq periods)]
  (intern *ns*
          (symbol (str "on-" (name planet)))
          (fn [sec] (/ sec (* period 31557600)))))
