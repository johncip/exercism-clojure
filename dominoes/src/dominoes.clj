(ns dominoes)

(defn match? [[a b] [c d]]
  (or (nil? a) (= b c)))

;; checks if tiles can be played in the given order,
;; allowing only rotation. returns the rotated tiles if so.
(defn valid-middle [tiles]
  (letfn [(search [path [t & ts :as tiles]]
            (let [rt (reverse t)
                  tail (last path)
                  continue #(search (conj path %) ts)]
              (cond
                (empty? tiles)   path
                (empty? path)    (or (continue t) (continue rt))
                (match? tail t)  (continue t)
                (match? tail rt) (continue rt)
                :else nil)))]
    (search [] tiles)))

;; returns true if tiles can be played in the given order,
;; and first & last number are equal.
(defn valid? [tiles]
  (let [soln (valid-middle tiles)
        llast (comp last last)]
    (and soln
         (= (ffirst soln) (llast soln)))))

(defn without [freqs t]
  (let [n (dec (freqs t))]
    (if (zero? n)
      (dissoc freqs t)
      (assoc freqs t n))))

;; returns each valid order (inc. rotations) that tiles can be played in.
;; backtracking dfs.
(defn can-chain? [tiles]
  (letfn [(solve [soln freqs]
            (if (empty? freqs)
              (when (valid? soln) soln)
              (keep seq (map
                          #(when (valid-middle soln)
                             (solve (conj soln %) (without freqs %)))
                          (keys freqs)))))]
  (or (empty? tiles)
      (seq (solve [] (frequencies tiles))))))
