(ns run-length-encoding)

(defn run-length-encode
  "encodes a string with run-length-encoding"
  [plain-text]
  (let [text (str plain-text \$)
        pairs (partition 2 1 text)]
    (loop [res ""
           len 1
           pairs pairs]
      (let [[cur next] (first pairs)
            rem (rest pairs)]
        (cond
          (empty? pairs) res
          (= cur next)   (recur res (inc len) rem)
          (= len 1)      (recur (str res cur) 1 rem)
          :else          (recur (str res len cur) 1 rem))))))

(defn run-length-decode
  "decodes a run-length-encoded string"
  [cipher-text]
  (reduce
    (fn [res chunk]
      (let [digits (re-find #"\d+" chunk)]
        (if digits
          (let [len (Integer. digits)
                letter (last chunk)]
            (apply str res (repeat len letter)))
          (str res chunk))))
    ""
    (re-seq #"\d*[^\d]" cipher-text)))
