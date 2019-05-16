(ns secret-handshake)

(defn commands [n]
  (reduce
    (fn [res [k v]] (if (bit-test n k) (conj res v) res))
    (if (bit-test n 4) '() [])
    {0 "wink" 1 "double blink" 2 "close your eyes" 3 "jump"}))
