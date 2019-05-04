(ns allergies)

(def allergens
  [:eggs :peanuts :shellfish :strawberries
   :tomatoes :chocolate :pollen :cats])

(defn allergies [n]
  (keep-indexed #(if (bit-test n %1) %2) allergens))

(defn allergic-to? [n gen]
  ((set (allergies n)) gen))
