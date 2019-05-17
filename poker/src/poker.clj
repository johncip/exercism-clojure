(ns poker)

;; -- misc card & hand utils --

(def all-ranks (mapv str '(2 3 4 5 6 7 8 9 10 J Q K A)))
(def card-value (zipmap all-ranks (range)))
(def ranks (partial map first))
(def suits (partial map second))

(def grouped-ranks
  (comp vals (partial group-by identity) ranks))

(defn normalize [cards]
  (->> (re-seq #"\d+|[JQKA]|[CDHS]" cards)
       (partition 2)
       (sort-by (comp card-value first))))

;; -- tests for hand types --

(defn flush? [cs]
  (-> cs suits distinct count (= 1)))

(defn straight? [cs]
  (let [straights (set (partition 5 1 (concat ["A"] all-ranks)))
        rnks (ranks cs)
        rotated (conj (butlast rnks) (last rnks))]
    (or
      (contains? straights rnks)
      (contains? straights rotated)))) ;; try ace first

(defn group-of-size? [n cs]
  (some #(= (count %) n) (grouped-ranks cs)))

(def rules
  (into (sorted-map-by (comp - compare))
    {:9-straight-flush  (every-pred straight? flush?)
     :8-four-of-a-kind  #(group-of-size? 4 %)
     :7-full-house      (every-pred #(group-of-size? 3 %)
                                    #(group-of-size? 2 %))
     :6-flush           flush?
     :5-straight        straight?
     :4-three-of-a-kind #(group-of-size? 3 %)
     :3-two-pair        #(= 3 (count (grouped-ranks %)))
     :2-one-pair        #(= 4 (count (grouped-ranks %)))
     :1-high-card       (constantly true)}))

(defn hand [cards]
  (ffirst (filter #((last %) (normalize cards)) rules)))

;; -- sortable representation --

(defn fix-ace-low-straight [rep]
  (if (= rep [:5-straight 12 3 2 1 0])
    [:5-straight 3 2 1 0 -1]
    rep))

;; a "sortable representation" for the cards, which starts with a hand
;; name. the name is prefixed by a hand score (higher is better).
;; integer card values follow, sorted by group size (if any)
;; and then by value (decreasing).
;;
;; e.g. (:7-full-house 2 2 2 7 7)
;; e.g. (:5-straight 5 4 3 2 1)
(defn sortable-rep [cards]
  (->> cards
       normalize
       grouped-ranks
       (sort-by count)
       flatten
       reverse
       (map card-value)
       (concat [(hand cards)])
       vec
       fix-ace-low-straight))

;; -- entry point --

(defn best-hands [strs]
  (->> strs
       (mapv (juxt sortable-rep identity))
       (sort-by first)
       (partition-by first)
       last
       (map last)))
