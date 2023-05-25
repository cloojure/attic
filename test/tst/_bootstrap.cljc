(ns tst._bootstrap
  "This namespace is used to perform one-time tasks at the beginning of a test run,
   such as printing the Clojure version."
  (:use tupelo.test)
  (:require
    [schema.core :as s]
    [tupelo.core :as t]))

; #?(:cljs (enable-console-print!))

(s/set-fn-validation! true) ; enforce fn schemas

; Prismatic Schema type definitions
; #todo add to Schema docs
; (set! *warn-on-reflection* true)  ; #todo enable?

(verify
  (t/print-versions)
  ;(spyx (s/fn-validation?))

  (is (= 5 (+ 2 3)))
  (isnt (= 2 3))
  )

(comment
  #?(:clj
     (do
       (verify
         (newline)
         (let [traces       (Thread/getAllStackTraces)
               threads      (keys traces)
               names        (mapv #(.getName %) threads)
               name->thread (t/->sorted-map (zipmap names threads))
               ]
           (spyx (count traces))
           (doseq [[n t] name->thread]
             (spyx [n (.isDaemon ^Thread t)]))))
       )))
