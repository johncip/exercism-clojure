(ns dominoes)

(defn can-play? [[a b] [c d]]
  (or (nil? a) (= b c)))

;; see if tiles can be played in the given order,
;; allowing only rotation. returns the correctly
;; rotated list, or nil.
(defn valid-middle [tiles]
  (letfn [(play [played [t & ts :as tiles]]
            (let [rt (reverse t)
                  tail (last played)
                  continue #(play (conj played %) ts)]
              (cond
                (empty? tiles)      played
                (empty? played)     (or (continue t)
                                        (continue rt))
                (can-play? tail t)  (continue t)
                (can-play? tail rt) (continue rt)
                :else nil)))]
    (play [] tiles)))

(defn valid? [tiles]
  (let [soln (valid-middle tiles)
        llast (comp last last)]
    (and soln
         (= (ffirst soln) (llast soln)))))

;; use of frequencies is technically just a speedup
(defn without [freqs t]
  (let [n (dec (freqs t))]
    (if (zero? n)
      (dissoc freqs t)
      (assoc freqs t n))))

(defn solve [soln freqs]
  (if (empty? freqs)
    (when (valid? soln) soln)
    (filter seq
      (map
        #(when (valid-middle soln)
           (solve (conj soln %) (without freqs %)))
        (keys freqs)))))

(defn can-chain? [tiles]
  (if (empty? tiles)
    true
    (seq (solve [] (frequencies tiles)))))
