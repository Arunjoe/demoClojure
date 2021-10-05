(ns demoapp.core
 (:require  [monger.core :as mg]
            [monger.collection :as mc]
            [monger.conversion :refer [from-db-object]])
(:import org.bson.types.ObjectId))


;;------------------
;; (defn round
;;    [d precision]
;;    (let [factor (Math/pow 10 precision)]
;;      (/ (Math/floor (* d factor)) factor)))
;;    (println d)

;; (round 5 6)
;;----------------------------------------
(defn reader_function [command]
  (println command ": ")
  (def console_input (read-line))
  console_input)
;;----------------------------------------

(defn normal_function []
  (println "Normal function without arguments"))

(defn simple_square []
  (def x 6)
  (def result (* x x))
  (println result))

(defn Function_with_argument [argument1]
  (def concatinated_string (str "Passed argument is " argument1))
  (println concatinated_string))

;;----------------------------------------
(defn messenger
  ([]
   (messenger "World! "))
  ([msg]
   (def x (str "My dear " msg))
   (println x)))

;;----------------------------------------

(defn if_function [check]
  (if (<= check 10)
    (println "Passed value" check " is less than or equal to 10 ")
    (println "Passed value" check " is greater than 10 ")))

(defn nested_if [price_low price_high]
  (if (and (>= price_low 200) (<= price_high 500))
    (println "Eligilble for 20% discount")
    (println "Not eligible")))

;;----------------------------------------
(defn switch_case [x]
  (case x
    "apache" (println "Contact Alice to install apache")
    "nginx" (println "Contact Bob to arrange nginx installation")
    (println "You cannot install " x " software on this machine")))

;;----------------------------------------

(defn if_do [check]
  (if (> check 500)
    (do
      (println "Cost : " check)
      (println "You are eligible for discount")
      (println "And no shipping charge")
      (def shipping 0)
      (def discount 0.1)
      (def price (- check (* check discount)))
      (println "Total amount to be paid is " price))
    (do
      (println "Cost : " check)
      (println "Your total cost is less than 500")
      (println "There will be a shipping charge")
      (def shipping 40)
      (def price (+ check shipping))
      (println "Total amount to be paid is" price))))
;;----------------------------------------
(defn cond_evaluation [time]
  (print "Hi Alice, ")
  (cond
    (<= time 11) (println "Good Morning")
    (<= time 15) (println "Good afternoon")
    (<= time 19) (println "Good evening")
    (<= time 24) (println "Good night")
    :else (println "I don't know what time is this. Get some sleep")))

;;----------------------------------------
(defn anon_fn [x]
  (def square (fn [x] (* x x)))
  (println (square 6))
  (def area (fn [a b] (* 2 (+ a b))))
  (println (area 4 6)))

;;----------------------------------------
(defn let_keyword [msg]
  (let [a 7
        b 5
        c (clojure.string/capitalize msg)
        d (clojure.string/blank? msg)]
    (println a b c d)) ;; end of let scope
      ;;(println c) ;throws an error
) ;; end of function

;;----------------------------------------
(defn while_loop [limit]
  (def x (atom 1))
  (while (< @x limit)
    (do
         ;; (println @x)
      (if_function @x)
      (swap! x inc))))
;;----------------------------------------
(defn while_atom []
  (def length_of_list 5)
  (def i (atom 0))
  (while (< @i length_of_list)
    (do  (println "looping " @i)
         (swap! i inc)
         (println "---@i---" @i)
    )
    )
  )

;;----------------------------------------
(defn doseq_loop []
  (doseq [n [5 7 2]]
    (println n))
  (doseq [fruits ["apple" "mango" "grape"]]
    (println fruits))
  (doseq [execute_myFns [(normal_function) (simple_square)]]
    (execute_myFns))  ; ! some error exist. has to improvise. - Syntax error (NullPointerException)
  )
;;----------------------------------------
(defn dotimes_loop [x] ;it does the auto increment by itself
  (dotimes [n x]
    (println n)))
;;----------------------------------------
(defn range_loop [x y z]
  (def odd_numbers (filter odd? (range 0 y)))
  (def test_list (range x y z))
  (println odd_numbers)
  (println test_list))
;;----------------------------------------
(defn test_functions []
  (def x (list 4 6 -8 7 -2 0 3 1 -9 -5 2.5 6.3 -7.8))
  (def positive (filter pos? x))
  (println positive)
  (def negative (filter neg? x))
  (println negative)
  (def integers (filter int? x))
  (println integers)
  (def numbers (filter number? x))
  (println numbers)
  (def even_nums (filter even? integers))
  (println even_nums)
  (def odd_nums (filter odd? integers))
  (println odd_nums)
  (def floating_nums (filter float? numbers))
  (println floating_nums))

;;----------------------------------------
(defn loop_loop [mul]
  (loop [i 1]
    (when (<= i 10)
      (println i "x" mul "=" (* i mul))
      (recur (inc i)))))
;;----------------------------------------
(defn file_read_function []
;;    (def string1 (slurp "src\\demoapp\\Sample.txt"))
;;    (println string1)
;;   (println "------read--once----")
  (spit "src\\demoapp\\Sample.txt"
        "This is the added string")
  (def string2 (slurp "src\\demoapp\\Sample.txt"))
  (println string2))
;;----------------------------------------
(defn interactive_inputs []
  (println "May I know your good name")
  (def console_line (read-line))
  (println "Please share your email id ?")
  (def email (read-line))
  (println "Please enter your CGPA")
  (def cgpa (Float/parseFloat (read-line)))
  (println "Hi" console_line)
  (println "Your mark in percentage is" (* cgpa 10))
  (println "I'll be senting this document to " email))
  
;;----------------------------------------
;; (defn depen_check []
;;   (require '[timewords.core :as parser])
;;   (println (parser "2001-01-01"))
;;   (def now (timewords.core "now"))
;;   (println now)
;;   )
;;----------------------------------------
(defn mongodb[]

(let [conn (mg/connect)
      db   (mg/get-db conn "ransomware")]
  (mc/insert db "extensions" { :_id (ObjectId.) :extension ".yello" :year "2002" }))


)
;;----------------------------------------
(defn mongoupdate []

(let [conn (mg/connect)
      db   (mg/get-db conn "ransomware")
      coll "extensions"]
  (mc/update db coll {:extension ".thor"} {:extension ".Dcrypt"} {:multi false}))
  
  )
;;----------------------------------------

(defn deleting_from_db[]
  (let [conn (mg/connect)
      db   (mg/get-db conn "ransomware")
      coll "extensions"]
  ;; (def delete_name (reader_function "Which user do you wanna delete ?"))
  (def delete_name ".yello")
  (def y (mc/remove db coll { :extension delete_name }))
  (println "-----------------removed------------")
  (println y)
  (def wr (second y))
  (println wr)
  )
)
;;----------------------------------------
(defn db_query[]
  (let [conn (mg/connect)
      db   (mg/get-db conn "ransomware")
      coll "extensions"]
(def matching_doc (mc/find-maps db coll { :extension ".thor" }))
(println matching_doc)

(def matching_name ((select-keys (first matching_doc) [:extension]) :extension))
(println matching_name)
  )
)
;;----------------------------------------


(defn -main [& args]
;; (normal_function)
;; (Function_with_argument "myArgument")
;; (messenger "John")
;; (messenger)
;; (if_function 10)
;; (nested_if 300 400)
;; (switch_case "nginx")
;; (switch_case "no_data")
;; (if_do 2000)
;; (cond_evaluation 23)
;; (anon_fn)
;; (let_keyword "myLowerCaseLetters")
;; (while_loop 15)
  ;; (while_atom)
;; (doseq_loop)
;; (dotimes_loop 5)
;; (range_loop 0 24 3)
;; (test_functions)
;; (loop_loop 7)
;; (file_read_function)
;; (interactive_inputs)
;; (depen_check)
;; (mongodb)
;; (mongoupdate)
;; (deleting_from_db)
(db_query)

  (println "----------------------------Execution Completed----------------------------"))





;; ------------Delete no such document------------- 
;;  #object[com.mongodb.WriteResult 0x7e053511 WriteResult{, n=0, updateOfExisting=false, upsertedId=null}]
;; ----------------------------Execution Completed----------------------------

;; ------------Delete Success------------- 
;;  #object[com.mongodb.WriteResult 0x7e053511 WriteResult{, n=1, updateOfExisting=false, upsertedId=null}]
;; ----------------------------Execution Completed----------------------------
