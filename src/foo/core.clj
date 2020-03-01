(ns foo.core
  (:require [medley.core :as medley]))

(defn -main []
  (println (medley/map-keys name {:hello "world"})))
