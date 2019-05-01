(ns space-age)

(def fname->period
  {'on-earth   1         'on-mercury 0.240846
   'on-venus   0.615197  'on-mars    1.880815
   'on-jupiter 11.86261  'on-saturn  29.44749
   'on-uranus  84.01684  'on-neptune 164.7913})

(doseq [[fname period] (seq fname->period)]
    (intern *ns* fname #(/ % (* period 31557600))))
