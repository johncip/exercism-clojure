(ns robot-simulator)

(defn robot [pos dir]
  {:coordinates pos :bearing dir})

(def moves
  {:north {:x 0 :y 1} :east {:x 1 :y 0}
   :south {:x 0 :y -1} :west {:x -1 :y 0}})

(def turn-right
  {:north :east, :east :south
   :south :west, :west :north})

(defn turn-left [dir]
  (nth (iterate turn-right dir) 3))

(defn dispatch [bot cmd]
  (let [unit (moves (:bearing bot))
        advance #(merge-with + % unit)]
    (case cmd
      \A (update bot :coordinates advance)
      \R (update bot :bearing turn-right)
      \L (update bot :bearing turn-left))))

(defn simulate [cmds bot]
  (reduce dispatch bot cmds))
