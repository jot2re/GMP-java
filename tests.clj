(ns GMP.tests
  (:use clojure.contrib.test-is))
  
(import GMP)
(import java.util.Random)
(import java.math.BigInteger)

(def a (new GMP "23"))
(def b (new GMP "100"))
(def res (new GMP))

;; (deftest test-constructor3
;; (def bytes (.toByteArray a))
;; (def c (new GMPInteger 1 bytes))
;; (are (= _1 _2)
;;      0 (.compareTo c (new GMPInteger "23"))
;;      0 (.compareTo b (new GMPInteger 1 (.toByteArray b)))))

(deftest test-plus
     (.add a b res) 
     (is (= res (new GMP "123")))
     (.add b a res) 
     (is (= res (new GMP "123")))
     (.add a a res) 
     (is (= res (new GMP "46")))
     (.add b b res) 
     (is (= res (new GMP "200")))
)

(deftest test-minus
  (.subtract a b res)
  (is (= res (new GMP -77)))
)

(deftest test-mul
  (.multiply a b res)
  (is (= res 2300))
)

(deftest test-div
     (.divide a b res) 
     (is (= res 0))
     (.divide b a res) 
     (is (= 4 res))
)

(deftest test-rem
  (.remainder a b res)
  (is (= 23 res))
  (.remainder b a res)
  (is (= 8 res))
)

(deftest test-mod
  (.mod a a res)
  (is (= 0 res))

  (.mod b a res)
  (is (= 8 res))
)

(deftest test-div-rem
  (def q (new GMP))
  (def r (new GMP))
  (.quotientAndRemainder b a q r)
  (are (= _1 _2)
       q (new GMP "4") 
       r (new GMP "8"))
)

(deftest test-pow
  (.pow a 0 res)
  (is (= 1 res))
  (.pow a 2 res)
  (def c (new GMP))
  (.multiply a a c)
  (is (= c res))
)

(deftest test-mod
  (.mod a a res)
  (is (= 0 res))
  (.mod b a res)
  (is (= 8 res))
)

(deftest test-mod-inverse
  (.modInverse a b res)
  (is (= 87 res))
  (.mod b b res)
  (is (= 0 res))
)

(deftest test-mod-pow
  (.modPow (new GMP "12") a a res)
  (is (= 12 res))
  
  (.modPow (new GMP "112") b a res)
  (is (= 3 res))

  (.modPow (new GMP "37") b a res)
  (is (= 9 res))
)
 
;; (deftest test-gcd
;; (are (= _1 _2)
;;      (new GMPInteger "1") (.gcd (new GMPInteger "12") a )
;;      (new GMPInteger "100") (.gcd (new GMPInteger "-300") b ) 
;;      (new GMPInteger "100") (.gcd (new GMPInteger "100") b )))

;; (deftest test-is-probable-prime
;; (are (= _1 _2)
;;      true (.isProbablePrime a 25)
;;      false (.isProbablePrime b 25 ) 
;;      true (.isProbablePrime (new GMPInteger "1166727135273561043807935164711") 25 ))) 

;; (deftest test-signum
;; (are (= _1 _2)
;;      1 (.signum a ) 
;;      -1 (.signum (.negate b ))))

;; (deftest test-compare-to
;; (are (= _1 _2)
;;      -1 (.compareTo a b) 
;;      -1 (.compareTo (.negate b ) a)))

;; (deftest test-min
;; (are (= _1 _2)
;;      a (.min a b) 
;;      a (.min a a)))

;; (deftest test-max
;; (are (= _1 _2)
;;      b (.max a b) 
;;      a (.max a a)))

;; (deftest test-shift-left
;; (are (= _1 _2)
;;      (new GMPInteger "92") (.shiftLeft a 2)
;;      a (.shiftLeft a 0 )))

;; (deftest test-shift-right
;; (are (= _1 _2)
;;      (new GMPInteger "400") (.shiftLeft b 2)
;;      a (.shiftLeft a 0 )))

;; (deftest test-to-string
;; (are (= _1 _2)
;;      "23" (.toString a)
;;      "1100100" (.toString b 2 )))

;; (deftest test-int-value
;; (are (= _1 _2)
;;      23 (.intValue a)
;;      100 (.intValue b )))

;; (deftest test-long-value
;; (are (= _1 _2)
;;      4294967297 (.longValue (new GMPInteger "4294967297"))
;;      100 (.longValue b )))

;; (deftest test-equals
;; (are (= _1 _2)
;;      false (.equals b (new GMPInteger "4294967297"))
;;      true (.equals a (new GMPInteger "23"))
;;      true (.equals a a)
;;      false (.equals 23 a)))

;; (deftest test-double-value
;; (are (= _1 _2)
;;      42949.0 (.doubleValue (new GMPInteger "42949"))
;;      100.0 (.doubleValue b )))

;; (deftest test-float-value
;; (are (= _1 _2)
;;      42949.0 (.floatValue (new GMPInteger "42949"))
;;      100.0 (.floatValue b )))

;; (deftest test-abs
;; (are (= _1 _2)
;;      (new GMPInteger "23") (.abs a)
;;      (new GMPInteger "231") (.abs (new GMPInteger "-231"))))

;; (deftest test-negate
;; (are (= _1 _2)
;;      (new GMPInteger "-23") (.negate a)
;;      (new GMPInteger "231") (.negate (new GMPInteger "-231"))))

;; (deftest test-to-bit-length
;; (are (= _1 _2)
;;      5 (.bitLength a)
;;      8 (.bitLength (new GMPInteger "-129"))))

;; (deftest test-to-byte-array
;; (def c (new BigInteger "23"))
;; (def d (new BigInteger "100"))
;; (are (= _1 _2)
;;      (first (.toByteArray a)) (first (.toByteArray c)) 
;;      (rest (.toByteArray d)) (rest(.toByteArray b))))

;; (deftest test-and
;; (are (= _1 _2)
;;      (new GMPInteger "0") (.and a (new GMPInteger "0")) 
;;      (new GMPInteger "100") (.and b b)))

;; (deftest test-or
;; (are (= _1 _2)
;;      (new GMPInteger "23") (.or a (new GMPInteger "0")) 
;;      (new GMPInteger "119") (.or a b)))

;; (deftest test-xor
;; (are (= _1 _2)
;;      (new GMPInteger "23") (.xor a (new GMPInteger "0")) 
;;      (new GMPInteger "115") (.xor a b)))

;; (deftest test-not
;; (are (= _1 _2)
;;      (new GMPInteger "-24") (.not a ) 
;;      (new GMPInteger "-101") (.not b)))

;; (deftest test-and-not
;; (are (= _1 _2)
;;      (new GMPInteger "19") (.andNot a b) 
;;      (new GMPInteger "96") (.andNot b a)))

;; (deftest test-and-not
;; (are (= _1 _2)
;;      (new GMPInteger "19") (.andNot a b) 
;;      (new GMPInteger "96") (.andNot b a)))

;; (deftest test-clear-bit
;; (are (= _1 _2)
;;      ; "1100100"
;;      (new GMPInteger "96") (.clearBit b 2) 
;;      (new GMPInteger "100") (.clearBit b 1)))

;; (deftest test-set-bit
;; (are (= _1 _2)
;;      ; "1100100"
;;      (new GMPInteger "100") (.setBit b 2) 
;;      (new GMPInteger "101") (.setBit b 0)))

;; (deftest test-test-bit
;; (are (= _1 _2)
;;      ; "1100100"
;;      true (.testBit b 2) 
;;      false (.testBit b 0)))

;; (deftest test-flip-bit
;; (are (= _1 _2)
;;      ; "1100100"
;;      (new GMPInteger "96") (.flipBit b 2) 
;;      (new GMPInteger "116") (.flipBit b 4)))

;; (deftest test-get-lowest-set-bit
;; (are (= _1 _2)
;;      ; "1100100"
;;      7 (.getLowestSetBit (new GMPInteger "128") )
;;      0 (.getLowestSetBit (new GMPInteger "129"))))

;; (deftest test-bit-count
;; (are (= _1 _2)
;;      ; "1100100"
;;      1 (.bitCount (new GMPInteger "128") )
;;      7 (.bitCount (new GMPInteger "-127"))))

;; (deftest test-hashcode )

;; run the tests
(run-tests 'GMP.tests)
