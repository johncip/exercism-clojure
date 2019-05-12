(ns bracket-push)

(def right-for (zipmap "([{" ")]}"))
(def left? (set (keys right-for)))

(defn on-stack [[k & ks :as stack] c]
  (cond
    (left? c) (conj stack (right-for c))
    (= c k)   ks
    :else     (reduced '($))))

(defn brackets [s]
  (filter #{ \( \) \[ \] \{ \} } s))

(defn valid? [s]
  (empty? (reduce on-stack '() (brackets s))))
