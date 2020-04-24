#!/usr/bin/env bb

(defn- test-code
  [namespaces]
  `(let [ns-syms# (map symbol ~namespaces)]
     (doseq [x# ns-syms#]
       (require (symbol x#)))

     (let [res# (apply clojure.test/run-tests ns-syms#)]
       ;(println (:out res#))
       (System/exit (+ (:fail res#) (:error res#))))))

(when (not= 2 (count *command-line-args*))
  (println "FIXME")
  (System/exit 1))

(let [[classpaths namespaces] *command-line-args*
      classpaths (str/replace classpaths #" " ":")
      namespaces (str/split namespaces #" ")
      {:keys [exit out]} (apply shell/sh ["bb" "--classpath" classpaths
                                          (str (test-code namespaces))])]
  (println out)
  (System/exit exit))
