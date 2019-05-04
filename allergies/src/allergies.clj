(ns allergies)

(def allergens
  [:eggs :peanuts :shellfish :strawberries
   :tomatoes :chocolate :pollen :cats])

(defn on? [i n]
  (-> (bit-shift-left 1 i) (bit-and n) pos?))

(defn allergies [n]
  (keep-indexed #(if (on? %1 n) %2) allergens))

(defn allergic-to? [n gen]
  ((set (allergies n)) gen))
