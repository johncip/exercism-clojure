(ns secret-handshake)

(defn commands [n]
  (reduce
    (fn [res [k v]] (if (pos? (bit-and n k)) (conj res v) res))
    (if (pos? (bit-and n 16)) '() [])
    {1 "wink" 2 "double blink" 4 "close your eyes" 8 "jump"}))
