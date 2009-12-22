(ns circumspec.context-test
  (:refer-clojure :exclude (assert)) 
  (:use circumspec)
  (:require [circumspec.context :as c]))

(describe c/context-form?
  (it "recognizes forms that establish circumspec context"
    (for-these [result form] (assert (= result (c/context-form? form)))
               false 1
               false '(foo)
               true '(describe)
               true '(it))))

(describe c/test-function-metadata
  (it "with a non-empty body"
    (assert (= {:circumspec/spec true
                :circumspec/name "foo"
                :circumspec/context 'circumspec.context/*context*}
               (c/test-function-metadata "foo" '(list)))))
  (it "tests with empty bodies are pending"
    (assert (= {:circumspec/spec true
                :circumspec/name "foo"
                :circumspec/context  'circumspec.context/*context*
                :circumspec/pending true}
               (c/test-function-metadata "foo" '())))))