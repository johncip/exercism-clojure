(ns secret-handshake)

(defn commands [n]
  ((if (bit-test n 4) reverse identity)
   (keep-indexed #(if (bit-test n %1) %2)
     ["wink" "double blink" "close your eyes" "jump"])))
