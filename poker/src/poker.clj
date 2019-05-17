(ns poker)

(def all-ranks (mapv str '(2 3 4 5 6 7 8 9 10 J Q K A)))
(def card-value (zipmap all-ranks (iterate inc 2)))
(def ranks (partial map first))
(def suits (partial map second))
(def straights (set (partition 5 1 (concat ["A"] all-ranks))))

(def grouped-ranks
  (comp vals (partial group-by identity) ranks))

(defn normalize [cards]
  (->> (re-seq #"\d+|[JQKA]|[CDHS]" cards)
       (partition 2)
       (sort-by (comp card-value first))))

(defn flush? [cs]
  (-> cs suits distinct count (= 1)))

(defn straight? [cs]
  (let [rnks (ranks cs)
        rotated (conj (butlast rnks) (last rnks))]
    (or (contains? straights rnks)
        (contains? straights rotated)))) ;; try ace first

(defn has-group-of? [n cs]
  (some #(= (count %) n) (grouped-ranks cs)))

(def rules
  (into (sorted-map-by (comp - compare))
    {:9-straight-flush  (every-pred straight? flush?)
     :8-four-of-a-kind  #(has-group-of? 4 %)
     :7-full-house      (every-pred #(has-group-of? 3 %) #(has-group-of? 2 %))
     :6-flush           flush?
     :5-straight        straight?
     :4-three-of-a-kind #(has-group-of? 3 %)
     :3-two-pair        #(= 3 (count (grouped-ranks %)))
     :2-one-pair        #(= 4 (count (grouped-ranks %)))
     :1-high-card       (constantly true)}))

(defn hand [cards]
  (ffirst (filter #((last %) (normalize cards)) rules)))

(defn fix-ace-low-straight [rep]
  (if (= rep [:5-straight 14 5 4 3 2])
    [:5-straight 5 4 3 2 1] rep))

(defn sortable-rep [cards]
  (->> (normalize cards)
       grouped-ranks
       (sort-by count)
       flatten
       reverse
       (map card-value)
       (concat [(hand cards)])
       vec
       fix-ace-low-straight))

(defn best-hands [hands]
  (->> (mapv (juxt sortable-rep identity) hands)
       (sort-by first)
       (partition-by first)
       last
       (map last)))
