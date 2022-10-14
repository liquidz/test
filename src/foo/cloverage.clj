(ns foo.cloverage
  (:require
    [cloverage.report :as report]))

(defn reporter-fn
  "cloverage のカスタムレポーター
  サマリの合計値のみを GitHub Actions の Output として出力する"
  [{:keys [forms]}]
  (let [totalled-stats (report/total-stats forms)
        github-output (System/getenv "GITHUB_OUTPUT")]
    (println (System/getenv "HOGE"))
    (println (System/getenv "GITHUB_OUTPUT"))
    ;; (println (format "::set-output name=forms::%.2f%%" (:percent-forms-covered totalled-stats)))
    ;; (println (format "::set-output name=lines::%.2f%%" (:percent-lines-covered totalled-stats)))))
    (when github-output
      (spit github-output (format "forms=%.2f%%\n" (:percent-forms-covered totalled-stats)) :append true)
      (spit github-output (format "lines=%.2f%%\n" (:percent-lines-covered totalled-stats)) :append true))))
