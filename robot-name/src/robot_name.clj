(ns robot-name)

;; note: this doesn't satisfy the "guaranteed to be unique" requirement.

(defn random-char []
  (char (+ (rand 26) 65)))

(defn random-digit []
  (char (+ (rand 10) 48)))

(defn new-name [& args]
  (apply str (concat
               (repeatedly 2 random-char)
               (repeatedly 3 random-digit))))

(defn robot []
  (atom (new-name)))

(defn robot-name [robot]
  @robot)

(defn reset-name [robot]
  (swap! robot new-name))
