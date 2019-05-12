(ns bracket-push)

(def left? #{\( \[ \{})
(def right-for {\{ \} \[ \] \( \)})

(defn brackets [s]
  (filter #{ \( \) \[ \] \{ \} } s))

(defn on-stack [[k & ks :as stack] c]
  (cond
    (left? c) (conj stack (right-for c))
    (= c k)   ks
    :else     (reduced '($))))

(defn valid? [s]
  (empty? (reduce on-stack '() (brackets s))))
