(ns demoapp.core
 (:require  [monger.core :as mg]
            [monger.collection :as mc]
            ;; [monger.conversion :refer [from-db-object]]
            ;; [io.pedestal.http :as http]
            [clj-pdf.core :as pdf]
            [clojure.tools.logging :as log]
            [clojure.java.io :as io]
            [clojure.string :as cljs]
            
            [demoapp.jsontocsv :as j2csv]
            [demoapp.csvtojson :as csv2j]
            [demoapp.catsndogs :as cnd]
            [demoapp.stringOperations :as stringOp]
            )
  ;; (:use clj-pdf.core :refer pdf)
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
   (println x))
    ([msg msg2]
   (def x (str "My dear " msg "msg2" msg2))
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

;; Nested if -----------------------------------------


;;   ? :  :
;;----------------------------------------
(defn switch_case [x]
  (case x
    "apache" (println "Contact Alice to install apache")
    "nginx" (println "Contact Bob to arrange nginx installation")
    (println "You cannot install " x " software on this machine")
    (println "You cannon this machine"))
    )

;; multiple default condition 

(defn multiple_switches[x]
  (println "you are requesting help with " x)
  (let [person (atom nil)]
   (case x
     "react" (do (reset! person "John")(println "Refer react.com for help."))
     "clojure" (do (reset! person "Maria")(println "Refer clojure.org for more information"))
     "backend" (do (reset! person "Alice or Maria")(println "Refer clojure.org and java.com for learning materials"))
     "clojurescript" (do (reset! person "Roy or Maria")(println "Refer clojurescript.org for examples"))
     ("nodejs" "angular") (do (reset! person "Joe")(println "For Node JS or Angular refer company learning library"))
    ;;  "angular" 
     (do (reset! person "David")(println "This is a not dev related issue. IT team will support you"))
     )
    (println "contact" @person "for any assistance")))

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

(defn calculate
  [operator x y]
    (condp = operator
      "mul" (* x y)
      "add" (+ x y)))

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
(defn anon_fn []
  (def square (fn [x] (* x x)))
    (def area (fn [a b] (* 2 (+ a b))))
  (println (square (area 4 6)))
  (println (area 4 6)))

;;----------------------------------------
(defn let_keyword [msg]
  (let [a 7
        b 5
        c (clojure.string/capitalize msg)
        d (clojure.string/blank? msg)]
    (println a b c d)
    (if ()
(let [a1 ""
      a2 ""])
      )
    ) ;; end of let scope
      ;;(println c) ;throws an error
) ;; end of function

;;----------------------------------------
(defn while_loop [limit]
  (def x (atom 1))  ;; retain previous value ?? ref ?
  (while (< @x limit)
    (do
         ;; (println @x)
      (if_function @x)
      (swap! x inc)))
  ;;memoise =========================================
  )
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

;;; with string length count . .
;;----------------------------------------
(defn range_loop [x y z]
  (def odd_numbers (filter odd? (range 0 y)))
  (def test_list (range x y z))
  (println odd_numbers)
  (println test_list))

;; diff between dotimes x range
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

;; ?? recur x loop
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

(defn db []
  (def conn (mg/connect))
  (def db (mg/get-db conn "usersdb"))
  (def coll "users"))
;;----------------------------------------
(defn db_checker []
  (db)
  (def db_dump (mc/find-maps db coll))
  (println db_dump)
  (println type (db_dump)))

;;----------------------------------------
(defn text_file []
  (def string1 (slurp "resources/Sample.txt"))
   (println string1)
  (println (type string1))
  )

;;----------------------------------------

(defn text-line-by-line[]
  (with-open [rdr (io/reader "resources/Sample.txt")]
  ;;  (reduce conj [] (line-seq rdr))))
;; (println (line-seq rdr))))
(count (line-seq rdr))))


;;----------------------------------------
(defn write-to-text-file[]
  (spit "resources/Sample.txt"   ;;file location // url path // browser file paths
      ;; "A new conent added" :append true))
        "\n A new string added on new line" :append true))
;;----------------------------------------
(defn write-to-file-line-by-line[]
  (with-open [w (io/writer "resources/Sample.txt" :append true)]
      (.write w (str "\nhello" "world"))
    (println "added a line")
    (.write w "\nThis is added from clojure")
    (println "line b added")
    (.write w "\nLast line added")))


;;----------------------------------------
;; (defn file-to-array []
;;   (def file_dump (slurp "resources/Sample.txt"))
;;   (def file_splited (cljs/split file_dump #"\n"))
;;   ;; (println file_splited)
;;   (println "-----+++++++++-----")
;;   (println (second file_splited))
;;   (doseq [my_line file_splited]
;;     (println ">>>" my_line))
;;   ;; (println (type file_splited))
;;   )

;;----------------------------------------
(defn delete-a-line []
  (def line_to_delete "helloworld")  ;;we need to delete the line which is "helloworld"
  (def file_dump (slurp "resources/Sample.txt"));;reading from file using slurp method
  (println "--File read---") 
  (def file_splited (clojure.string/split file_dump #"\n")) ;;each line is splited to vector items with new-line "\n"
  (with-open [w (io/writer "resources/Sample.txt")] ;;opening the same file for writing
    (doseq [my_line file_splited] ;;looping through the vector of lines
      (if (not= my_line line_to_delete) ;;condition
        (.write w (str my_line "\n")) ;;writing to file. to update the line, include modifiction functions with do block
        (println "deleting" my_line)))) ;;add functions as required
  (println "writing completed"))

;;--------------
(defn delete-a-word []
  (def line_to_delete "python")  ;;
  (def replacement "clojure")
  (def file_dump (slurp "resources/badfile.txt"));;reading from file using slurp method
  (println "--File read---") 
  (def file_splited (clojure.string/split file_dump #"\n")) ;;each line is splited to vector items with new-line "\n"
  (with-open [w (io/writer "resources/corrected.txt")] ;;opening the same file for writing
    (doseq [my_line file_splited] ;;looping through the vector of lines
      (if (clojure.string/includes? my_line line_to_delete) ;;condition
        (.write w (str (clojure.string/replace my_line line_to_delete replacement) "\n")) ;;writing to file. to update the line, include modifiction functions with do block
        (.write w (str my_line "\n"))))) ;;add functions as required
  (println "writing completed"))

;;----------------------------------------

;;substring 
(defn blacklist[raw_string]
  (def blacklist_characters #"[!@#$%^&*('/)<>;:{}]")
  (let [sanitized_string (clojure.string/replace raw_string blacklist_characters "")]
    (println "input string: \t \t" raw_string)
    (println "sanitized string: \t"sanitized_string))
(def numberss #"[1234567]")  
  (println (clojure.string/includes? raw_string "4"))
  )
;----------------------------------------
(defn print-nums-only [s]
(re-seq #"(?i)[1234567890]" s))
;;----------------------------------------

;;----------------------------------------

(defn json_check []
  (db)
  (def dz `[{:a 2, :b 3, :c 4} {:a 5, :b 6, :c 7}])
  ;; (def js(http/json-response da))
  (def da (mc/find-maps db coll))
  (println da)
  (println "---------------got data------------------")
  ;; (println  da)
  (println  dz)
  (def da_vec (vec da))
  (println da_vec)
  (println "-----"))
;;----------------------------------------
(defn export_pdf[]
  (let [name "John"
        id 24578
        number 98745872
        address "Bangalore, IN"]
(pdf/pdf
  [{}
   [:list {:symbol "-"}
    [:chunk {:style :bold} (str "Name:" name)]
    (str "User Id:" id)
    (str "Number:" number)]
   [:paragraph address]
   [:line]
   [:paragraph (str name " is working as Associate Software Developer since 2018")]
   [:paragraph "He has 6+ years of experience in web technologies including js, angular, php, and .NET"]]
  "./resources/docJohn.pdf")    
    ))


;;----------------------------------------
(defn csv_to_json[]
  )
;;----------------------------------------

(defmacro with-try-catch
  [& body]
  `(try
     ~@body
    ;;  (catch Throwable t# '(log/error t#))  ;; logs the error
    ;;  (catch Exception t# (println (str "caught exception: " (.toString t#)))) ;;catches the exception only
     (catch Throwable t# (println (str "caught exception: " (.toString t#))))  ;;catches 
     ))
;;----------------------------------------
(defn exception_macro
  [c]
  (with-try-catch
    (dosync
     (let [x (Integer/parseInt c)]
       (println "Parsed Number is" x)
       (println (/ 10 x))
       (println "Completed without any excpetions")))))
;;----------------------------------------

(defn nested_let []
  (let [a 10
        b 20]
    (def junk_var 12)
    (let [res (+ a b)]
      (println "result is" res a b))))
;;----------------------------------------
(defn my-memoize-fn[a p]
  (-> (reduce * (repeat p a))
      (println)))

(defn demo_memoize[]
  (def memoized-fn (memoize my-memoize-fn))
  (time (memoized-fn 4 25))
  (println "Big calculation is done")
  (time (memoized-fn 4 25))
  (println "some more oprtations done")
  (println "Memoized function call: ")
  (time (memoized-fn 4 25))
  (println "Non-Memoized funtion call: ")
  (time (my-memoize-fn 4 25))
  )

;;----------------------------------------
(defn copy-file [source-path dest-path]
  (io/copy (io/file source-path) (io/file dest-path)))
(defn file-copier[]
  (copy-file "/resources/Sample.txt" "/resources/copied_sample.txt"))
;;----------------------------------------

;;----------------------------------------

;;----------------------------------------

;;----------------------------------------




(defn -main [& args]
;; (normal_function)
;; (Function_with_argument "myArgument")
;; (messenger "John" "Alice")
;;   (messenger "john" nil)
;; (messenger)
;; (if_function 10)
;; (nested_if 300 400)
;; (switch_case "nginx")
;; (switch_case "no_data")
  ;; (multiple_switches "angular")
;;  (if_do 2000)
;; (cond_evaluation 13)
  ;; (time (calculate "mul" 2 3))
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
;; (db_query)
  ;; (db_checker)
  ;; (json_check)
;; (text_file)
;; (let [x (text-line-by-line)] (println x))
;; (write-to-text-file)
;; (write-to-file-line-by-line)
  ;; (file-delete)
  ;; (file-to-array)
  ;; (delete-a-line)
  ;; (delete-a-word)
  ;; (clojure.set/subset? )

  ;; (export_pdf)
  ;; (j2csv/json_to_csv "./resources/aws.json" "./resources/aws0945.csv" "user.id,user.name,loc,post")
  ;; (csv2j/csv_to_json "./resources/students.csv" "./resources/studt.json" "1:student.name:str,2:student.id:int,3:student:address:str,7:student.contact:str,4:firm.name:str,5:firm.ctc:float,6:firm.location:str")

  ;; (exception_macro "0")
  ;; (nested_let)
  ;; (demo_memoize)
  ;; (file-copier)
  ;; (blacklist "<script>This is a hack initilizing at 12.30 (alert('Hacked'))</script>")
  ;; (println (print-nums-only "the annual turnover of big4 is above 245.3685 billion dollars in the year 2019"))

  ;; (cnd/catsdogs "I joined at tata as cyber security engineer" "cat" "ZQ" "dog" "cry" "data" "circuit")
(stringOp/string_file_op)

  (println "----------------------------Execution Completed----------------------------"))

