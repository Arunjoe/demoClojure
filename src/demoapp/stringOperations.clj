(ns demoapp.stringOperations
  (:require [clojure.string :as str]))
(defmacro with-try-catch
  [& body]
  `(try
     ~@body
     (catch Throwable t# (println (str "caught exception: " (.toString t#))))  ;;catches 
     ))
;; -------------------------------------------------------------------------------
(defn concatination [greeting name timing]
  (let [greet (str greeting name "," "Good" timing)]
    (println greet)))

;; -------------------------------------------------------------------------------
(defn formating[name timing]
  (let [good "Good"
        fir "FIRST"
        sec "SECOND"
        thd "THIRD"
        foo (format "Hello %s" name)
        bar (format "Hello %s, %s %s" name good timing)
        tim (format "it is %d:%d now" 12 30)
        lor (format "This is the  %2$s arguemt, this is the %3$s argument and finally this is the %1$s" sec thd fir)
        con (format "decimal %d octal %o  hex %x  upper-case hex %X" 43 43 43 43)
        flo (format "adding decimal %.3f ____ Pad with leading zeros %07d ____ adding seperators %,d " 5.0 5432 1234567894561)
        ]
    (println foo)
    (println bar)
    (println tim)
    (println lor)
    (println con "--->> binary not included")
    (println flo "\n ---->> float can be given as (double 5) or (double (/ 7 3)) ___ simply giving integers (format %3f 2) will throw error")
    ))

;; -------------------------------------------------------------------------------
(defn count-option[]
  (println "Counting nil: >>"(count nil))
  (println "Counting empty [] vector: >>"(count []))
  (println "Counting '[1 2 3 4 5]: >>"(count [1 2 2 4 5]))
  (println "Couting a string: >>"(count "String including few spaces"))
  (println "Counting {:a 1 :b b :c \"a string\" \"d\" 3.00}:: >>" (count {:a 1 :b 'b :c "a string" "d" 3.00}))
  (println "counting collecton with different types: >> " (count ["a" 2 ('b 'c) {:some "word"} \z]))
  (println "checking the given count is true or not:: >>>" (= (count '(1 2 3 3 1)) 3))
  
  (println "Counting the numbers only: >>"(count (re-seq #"(?i)[1234567890]" "127 hours is a film released in 2010. And collected 60.7million USD")))
  
  
  (println "\n============= END of count option ======================\n*************\n"))

;; -------------------------------------------------------------------------------
(defn substring-option [w]
  (println "Given Word: >> " w)
  (println "subs with start index only: >>" (subs w 3))
  (println "Subs with start and end index: >> " (subs w 2 5))
  (println "Subs with out of index option: >>" (with-try-catch (subs w 5 36)))
  (println "subs with reverse indexing: >>" (with-try-catch (subs w 6 2)))

  (println "\n============= END of substring option ======================\n*************\n"))
  
;; -------------------------------------------------------------------------------
(defn compare-option[]
  (println "Getting 0 means is same. any other value means not-same")
  (println "[0 1 2]x[0 1 2] : >>"(compare [0 1 2] [0 1 2]))
  (println "A x a : >>"(compare "A" "a") " ||  BC x bc : >>" (compare "BC" "bc"))
  (println "abc x ced : >>"(compare "abc" "ced"))
  (println "nil x nil  : >>"(compare nil []))
  (println "4 x 5 : >> "(compare 4 5))
  (println "true x false : >>"(compare true false))
  (println "false x false : >>"(compare false false))
  (println "with == 1 1 : >> "(== 1 1))
  
  (println "\n============= END of compare option ======================\n*************\n")
  )

;; -------------------------------------------------------------------------------
(defn letter-case[my_word]
  (println my_word "contians lower-case upper-case number and special chars")
  (println (str/lower-case my_word))
  (println (str/upper-case my_word))
  (print "trying nil: >>" (with-try-catch (str/lower-case nil)))
  (println "capitalizing first letter only: >>" (str/capitalize my_word))
  (println "no in built function for camel case. Refer the code below")
  (println "\n============= END of case option ======================\n*************\n")
  )

(defn capitalize-words 
  "Capitalize every word in a string"
  [s]
  (->> (str/split (str s) #"\b") 
       (map str/capitalize)
       str/join))

;; -------------------------------------------------------------------------------
(defn join-option[]
  (let [a (str/join "," [1 2 3])
        b (str/join " " ["United" "States" "Of" "America"])
        c (str/join ", " ["John" "Maria" nil "Lisa" "are from Bangalore"])]
    (println a)
    (println b)
    (println c))
  (println "\n============= END of case option ======================\n*************\n")
  
  )
;; -------------------------------------------------------------------------------
(defn split-options[s]
  (println s)
  (println "Space split: >>"(str/split s #" "))
  (println "Digits split1: >> "(str/split s #"\d"))
  (println "Digits split -- consequtive numbers are considered at once: >> "(str/split s #"\d+"))
  (println "Digits split with limit: >> "(str/split s #"\d+" 5))
  (println "Upper case split: >>"(str/split s #"(?=[A-Z])"))
  (println "string split: >>" (str/split s #"AB"))
  ;; (println "multple string match split: >>" (str/split s #"\A\B?\a\b"))
  (println "Special character split: >>"(str/split s #"(?=['! '@ '# '$ '% '^ '& '* '< '>)])"))
  (println "Not Matching split: >>"(str/split s #"a string which is not a substring of s"))
  (println "Same content --AAA-- and no spliting: >>"(str/split "AAA" #"AAA"))
  (println "White space split 2: >>"(str/split "Some words to split" #"\s+"))
  (println "split with limit: >>"(str/split "First second and third. spliting with limit 3 " #" " 3))
  (println "split-line do the same on \\n and \\r")
  (println "\n============= END of split option ======================\n*************\n")
  
  )

;; -------------------------------------------------------------------------------



;; -------------------------------------------------------------------------------
(defn frequency_count [a]
  (let [b (apply merge-with + (map #(hash-map % 1) (seq a)))]
    (prn b)))

(defn frequencies_inbuilt [a]
  (let [b (frequencies (seq a))]
    (prn b)))

;; -------------------------------------------------------------------------------
;;; ###############################################################################################################################
;;; ############################################################################################################################### 


(defn string_file_op []
;;   (time (frequency_count "This is my Clojure class"))
  ;; (time (frequencies_inbuilt "This is my Clojure class"))
  ;; ---------------------------------------------
  (concatination "Hello" "John" "Morning")
  (formating "John" "Evening")
  (count-option)
  (substring-option "Expensive weekend")
  (compare-option)
  (letter-case "This #123 seTANce hAs LoweR & uPPEr cAse letTErs ")
  (join-option)
  (split-options "a1b22c333d4444abcd AB@CD1234AB#CD123 QWE!@#45RTY")
  )
