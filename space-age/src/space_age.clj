(ns space-age)

(def periods
  {:earth   1         :mercury 0.240846
   :venus   0.615197  :mars    1.880815
   :jupiter 11.86261  :saturn  29.44749
   :uranus  84.01684  :neptune 164.7913})

(doseq [[planet period] (seq periods)]
  (let [fname (->> planet name (str "on-") symbol)]
    (intern *ns* fname
            #(/ % (* period 31557600)))))
