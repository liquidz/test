(ns build
  (:require
    [my-clj-build.core :as my]))
   ;; [clojure.tools.build.api :as b]
   ;; [deps-deploy.deps-deploy :as deploy]))

(def ^:private config
  {:lib 'com.github.liquidz/test
   :version "0.0.%s"
   :scm {:connection "scm:git:git://github.com/liquidz/test.git"
         :developerConnection "scm:git:ssh://git@github.com/liquidz/test.git"
         :url "https://github.com/liquidz/test"}})

(defn install
  [_]
  (my/install config))

(defn deploy
  [_]
  (my/deploy config))

;; (def ^:private version (format "0.0.%s" (b/git-count-revs nil)))
;; (def ^:private lib 'com.github.liquidz/test)
;; (def ^:private class-dir "target/classes")
;; (def ^:private jar-file "target/liquidz-test.jar")
;; (def ^:private scm {:connection "scm:git:git://github.com/liquidz/test.git"
;;                     :developerConnection "scm:git:ssh://git@github.com/liquidz/test.git"
;;                     :url "https://github.com/liquidz/test"
;;                     :tag version})

;; (defn pom
;;   [_]
;;   (let [basis (b/create-basis)]
;;     (b/write-pom {:basis basis
;;                   :class-dir class-dir
;;                   :lib lib
;;                   :version version
;;                   :src-dirs (:paths basis)
;;                   :scm scm})))
;;
;; (defn jar
;;   [arg]
;;   (let [basis (b/create-basis)]
;;     (pom arg)
;;     (b/copy-dir {:src-dirs (:paths basis)
;;                  :target-dir class-dir})
;;     (b/jar {:class-dir class-dir
;;             :jar-file jar-file})))
;;
;; (defn install
;;   [arg]
;;   (jar arg)
;;   (deploy/deploy {:artifact jar-file
;;                   :installer :local
;;                   :pom-file (b/pom-path {:lib lib :class-dir class-dir})}))
;;
;; (defn print-version
;;   [_]
;;   (println (str "::set-output name=version::" version)))
;;
;; (defn tag
;;   [_]
;;   (sh/sh "git" "tag" version)
;;   (sh/sh "git" "push" "origin" version))
;;
;; (defn deploy
;;   [arg]
;;   (assert (and (System/getenv "CLOJARS_USERNAME")
;;                (System/getenv "CLOJARS_PASSWORD")))
;;   (jar arg)
;;   (deploy/deploy {:artifact jar-file
;;                   :installer :remote
;;                   :pom-file (b/pom-path {:lib lib :class-dir class-dir})})
;;   (println (str "::set-output name=version::" version)))
;;
