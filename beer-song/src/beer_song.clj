(ns beer-song)

(defn if-one? [num yes no]
  (if (= 1 num) yes no))

(defn next-bottle [num]
  (if (= 1 num) "no more" (dec num)))

(defn bottles [num suffix]
  (str num " bottle" (if-one? num nil "s") " of beer" suffix))

(defn standard-verse [num]
  (let [article (if-one? num "it" "one")
        remaining (next-bottle num)]
    (str
      (bottles num " on the wall, ")
      (bottles num ".\n")
      "Take " article " down and pass it around, "
      (bottles remaining " on the wall.\n"))))

(defn last-verse []
  (str "No more bottles of beer on the wall, no more bottles of beer.\n"
       "Go to the store and buy some more, 99 bottles of beer on the wall.\n"))

(defn verse [num]
  (if (zero? num)
    (last-verse)
    (standard-verse num)))

(defn sing
  ([start] (sing start 0))
  ([start end] (->> (range start (dec end) -1)
                    (map verse)
                    (reduce #(str %1 "\n" %2)))))
