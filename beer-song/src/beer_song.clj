(ns beer-song)

(defn if-one? [num if_ else_]
  (if (= 1 num) if_ else_))

(defn next-bottle [num]
  (cond
    (= 1 num) "no more"
    (= "No more" num) 99
    :else (dec num)))

(defn bottles-of-beer [num suffix]
  (str num " bottle" (if-one? num nil "s")  " of beer" suffix))

(defn standard-verse [num]
  (let [article (if-one? num "it" "one")
        remaining (next-bottle num)]
    (str
      (bottles-of-beer num " on the wall, ")
      (bottles-of-beer num ".\n")
      "Take " article " down and pass it around, "
      (bottles-of-beer remaining " on the wall.\n"))))

(defn last-verse []
  (str
    (bottles-of-beer "No more" " on the wall, ")
    (bottles-of-beer "no more" ".\n")
    "Go to the store and buy some more, "
    (bottles-of-beer 99 " on the wall.\n")))

(defn verse [num]
  (if (zero? num)
    (last-verse)
    (standard-verse num)))

(defn sing
  ([start]
   (sing start 0))
  ([start end]
   (->> (range start (dec end) -1)
        (map verse)
        (reduce #(str %1 "\n" %2)))))
