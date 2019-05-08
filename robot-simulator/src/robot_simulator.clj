(ns robot-simulator)

(defn robot [pos dir]
  {:coordinates pos :bearing dir})

(def moves
  {:north {:x 0 :y  1} :east {:x  1 :y 0}
   :south {:x 0 :y -1} :west {:x -1 :y 0}})

(defn advance [bot]
  (let [{pos :coordinates, dir :bearing} bot]
    (assoc bot :coordinates (merge-with + pos (moves dir)))))

(defn turn-right [dir]
  (second (drop-while #(not= dir %) (cycle (keys moves)))))

(defn turn-left [dir]
  (nth (iterate turn-right dir) 3))

(defn simulate [codes bot]
  (let [ops {\A advance
             \R #(update % :bearing turn-right)
             \L #(update % :bearing turn-left)}]
    (reduce #(%2 %1) bot (map ops codes))))
