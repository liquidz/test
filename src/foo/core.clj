(ns foo.core
  (:require [medley.core :as medley]))

(defn- foo [arg]
  (+ 1 2 3))

(defn -main []
  (println (medley/map-keys name {:hello "world"})))
