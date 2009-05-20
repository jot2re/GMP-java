(ns GMPInteger.tests
  (:use clojure.contrib.test-is))
  
(import GMPInteger)

(def a (new GMPInteger "23"))
(def b (new GMPInteger "100"))

(deftest test-plus
(are (= _1 _2)
     123 (.add a b) "Test addition")
)

(deftest test-minus
(are (= _1 _2)
     -77 (.subtract a b) )
)

(deftest test-mul
(are (= _1 _2)
     2300 (.multiply a b) ) )

(deftest test-div
(are (= _1 _2)
     0 (.divide a b) 
     4 (.divide b a) )
)

(deftest test-rem
(are (= _1 _2)
     23 (.remainder a b) 
     8 (.remainder b a)))

(deftest test-mod
(are (= _1 _2)
     (new GMPInteger "0") (.mod a a) 
     (new GMPInteger "8") (.mod b a)))

(deftest test-div-rem
(def c (.divideAndRemainder b a))
(are (= _1 _2)
     (first c) (new GMPInteger "4") 
     (nth c 1) (new GMPInteger "8")))

(deftest test-pow
(are (= _1 _2)
     (new GMPInteger "1") (.pow a 0) 
     (.multiply a a) (.pow a 2)))

(deftest test-mod
(are (= _1 _2)
     (new GMPInteger "0") (.mod a a) 
     (new GMPInteger "8") (.mod b a)))

(deftest test-mod-inverse
(are (= _1 _2)
     (new GMPInteger "87") (.modInverse a b) 
     (new GMPInteger "0") (.modInverse b b)))

(deftest test-mod-pow
(are (= _1 _2)
     (new GMPInteger "12") (.modPow (new GMPInteger "12") a a)
     (new GMPInteger "3") (.modPow (new GMPInteger "112") b a) 
     (new GMPInteger "9") (.modPow (new GMPInteger "37") b a)))


;; run the tests
(run-tests 'GMPInteger.tests)
