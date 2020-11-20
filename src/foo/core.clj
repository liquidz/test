(ns foo.core)

(defn bar [x]
  (inc x))

(defn -main
  []
  (println "hello" (bar 1)))
