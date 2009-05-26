(ns GMP.tests
  (:use clojure.contrib.test-is))
  
(import GMP)
(import java.util.Random)
(import java.math.BigInteger)

(def a (new GMP "23"))
(def b (new GMP "100"))
(def res (new GMP))

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
 
(deftest test-gcd
  (.gcd (new GMP "12") a res)
  (is (= 1 res))
  
  (.gcd (new GMP -300) b res)
  (is (= 100 res))
  
  (.gcd (new GMP 100) b res)
  (is (= 100 res))
)

;; (deftest test-is-probable-prime
;; (are (= _1 _2)
;;      true (.isProbablePrime a 25)
;;      false (.isProbablePrime b 25 ) 
;;      true (.isProbablePrime (new GMPInteger "1166727135273561043807935164711") 25 ))) 

;; (deftest test-compare-to
;; (are (= _1 _2)
;;      -1 (.compareTo a b) 
;;      -1 (.compareTo (.negate b ) a)))


(deftest test-shift-left
  (.shiftLeft a 2 res)
  (is (= 92 res))

  (.shiftLeft a 0 res)
  (is (= a res))
)

(deftest test-shift-right
  (.shiftLeft b 2 res)
  (is (= 400 res))

  (.shiftLeft a 0 res)
  (is (= a res))
)

(deftest test-to-string
(are (= _1 _2)
     "23" (.toString a)
     "1100100" (.toString b 2 ))
)

(deftest test-int-value
(are (= _1 _2)
     23 (.intValue a)
     100 (.intValue b ))
)

(deftest test-long-value
(are (= _1 _2)
     4294967297 (.longValue (new GMP "4294967297"))
     100 (.longValue b ))
)

(deftest test-equals
(are (= _1 _2)
     false (.equals b (new GMP "4294967297"))
     true (.equals a a)
     false (.equals 23 a)))

(deftest test-double-value
(are (= _1 _2)
     42949.0 (.doubleValue (new GMP "42949"))
     100.0 (.doubleValue b )))

(deftest test-float-value
(are (= _1 _2)
     42949.0 (.floatValue (new GMP "42949"))
     100.0 (.floatValue b )))

(deftest test-abs
  (.abs a res)
  (is (= 23 res))
  
  (.abs (new GMP -231) res)
  (is (= 231 res))
)

(deftest test-negate
  (.negate a res)
  (is (= -23 res))
  
  (.negate (new GMP -231) res)
  (is (= 231 res))
)

(deftest test-to-bit-length
(are (= _1 _2)
     5 (.bitLength a)
     8 (.bitLength (new GMP "-129")))
)

(deftest test-to-byte-array
  (def c (new BigInteger "23"))
  (def d (new BigInteger "100"))
  (def r (make-array Byte/TYPE 1))
  (.toByteArray a r)
  (are (= _1 _2)
       (last r) (first (.toByteArray c)))
)

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
