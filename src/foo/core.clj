(ns foo.core
  (:require [medley.core :as medley]))

(defn -main [x]
  (println (medley/map-keys name {:hello "world"})))
