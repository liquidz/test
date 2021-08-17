(ns foo.cloverage
  (:require [cloverage.report :as report]))

(defn reporter-fn
  [{:keys [forms]}]
  (let [totalled-stats (report/total-stats forms)]
    (println (format "::set-output name=forms::%.2f%%" (:percent-forms-covered totalled-stats)))
    (println (format "::set-output name=lines::%.2f%%" (:percent-lines-covered totalled-stats)))))
