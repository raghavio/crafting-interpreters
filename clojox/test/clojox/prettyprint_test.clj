(ns clojox.prettyprint-test

  (:require [clojox.core :as clojox]
            [clojox.prettyprint :refer [pretty-print-ast]]
            [clojure.test :refer [deftest is testing]]))

(deftest test-pretty-print-ast
  (testing "pretty-print-ast 2 + 3"
    (let [binary-ast (clojox/run "2 + 3")]
      (is (= (pretty-print-ast binary-ast) ["+" 2.0 3.0]))))

  (testing "pretty-print-ast 2 - 3.56"
    (let [binary-ast (clojox/run "2 - 3.56")]
      (is (= (pretty-print-ast binary-ast) ["-" 2.0 3.56]))))

  (testing "pretty-print-ast 3/5 - (5 * 6)"
    (let [binary-ast (clojox/run "3/5 - (5 * 6)")]
      (is (= (pretty-print-ast binary-ast) ["-" ["/" 3.0 5.0] ["grouping" ["*" 5.0 6.0]]]))))

  (testing "pretty-print-ast !true == false"
    (let [binary-ast (clojox/run "!true == false")]
      (is (= (pretty-print-ast binary-ast) ["==" ["!" true] false]))))

  (testing "pretty-print-ast 4 * 5"
    (let [binary-ast (clojox/run "4 * 5")]
      (is (= (pretty-print-ast binary-ast) ["*" 4.0 5.0]))))

  (testing "pretty-print-ast 10 >= 5"
    (let [binary-ast (clojox/run "10 >= 5")]
      (is (= (pretty-print-ast binary-ast) [">=" 10.0 5.0]))))

  (testing "pretty-print-ast 7 < 8"
    (let [binary-ast (clojox/run "7 < 8")]
      (is (= (pretty-print-ast binary-ast) ["<" 7.0 8.0]))))

  (testing "pretty-print-ast 9 != 10"
    (let [binary-ast (clojox/run "9 != 10")]
      (is (= (pretty-print-ast binary-ast) ["!=" 9.0 10.0]))))

  (testing "pretty-print-ast (3 + 4) * 5"
    (let [binary-ast (clojox/run "(3 + 4) * 5")]
      (is (= (pretty-print-ast binary-ast) ["*" ["grouping" ["+" 3.0 4.0]] 5.0]))))

  (testing "pretty-print-ast -3"
    (let [unary-ast (clojox/run "-3")]
      (is (= (pretty-print-ast unary-ast) ["-" 3.0])))))
