(ns allergies)

(def allergens
  [:eggs :peanuts :shellfish
   :strawberries :tomatoes
   :chocolate :pollen :cats])

(defn on? [pow n]
  (-> (bit-shift-left 1 pow) (bit-and n) pos?))

(defn allergies [n]
  (filter some?
    (map-indexed #(if (on? %1 n) %2) allergens)))

(defn allergic-to? [n gen]
  (contains? (set (allergies n)) gen))
