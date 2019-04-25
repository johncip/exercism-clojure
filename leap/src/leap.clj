(ns leap)

(defn divides? [b a]
  (zero? (mod a b)))

(defn leap-year? [year]
  (condp divides? year
    400 true
    100 false
      4 true
        false))
