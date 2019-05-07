(ns bank-account)

(def open-account #(atom 0))

(def get-balance deref)

(defn close-account [ac]
  (reset! ac nil))

(defn update-balance [ac amt]
  {:pre [@ac]}
  (swap! ac + amt))
