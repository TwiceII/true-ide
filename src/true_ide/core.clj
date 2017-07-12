(ns true-ide.core
  (:gen-class))


(defn get-op
  [m]
  (if (= (:op m) :add)
    +
    -))


(defn calc
  [x y m]
  ((get-op m) x y))


(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))
