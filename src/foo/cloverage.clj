(ns foo.cloverage
  (:require
    [cloverage.report :as report]))

(defn reporter-fn
  "cloverage のカスタムレポーター
  サマリの合計値のみを GitHub Actions の Output として出力する"
  [{:keys [forms]}]
  (let [totalled-stats (report/total-stats forms)]
    (println (System/getenv "HOGE"))
    (println (System/getenv "GITHUB_OUTPUT"))
    (println (format "::set-output name=forms::%.2f%%" (:percent-forms-covered totalled-stats)))
    (println (format "::set-output name=lines::%.2f%%" (:percent-lines-covered totalled-stats)))))
