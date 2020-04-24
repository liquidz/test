(ns foo.core-test
  (:require [clojure.test :as t]
            [foo.core :as sut]))

(t/deftest bar-test
  (t/is (= 2 (sut/bar 1))))

