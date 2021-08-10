(ns foo.core-test
  (:require
    [cljs.test :as t :include-macros true]))

(t/deftest foo-test
  (t/is (= 1 1)))
