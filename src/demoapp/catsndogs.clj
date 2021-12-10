(ns demoapp.catsndogs
  (:require [clojure.string :as str]))

(def word_freq (atom {}))

(defn string-to-hashmap [sent]
  (let [freqq (frequencies (str/lower-case sent))]
    (doseq [[key value] freqq]
      (swap! word_freq assoc key value))))

(defn word-check [charmap word]
  (let [flag (atom 0)]
    (doseq [w word]
      (let [left_occ (get charmap w)]
        ;; (println "Matching count" w "::" left_occ)
        (if (and (not= nil left_occ) (not= 0 left_occ))
          (swap! word_freq assoc w (- left_occ 1))
          (swap! flag inc))))
    (if (= @flag 0)
      (println "++ " word)
      (println "-- " word))))


(defn catsdogs [sentance & words]
  (string-to-hashmap sentance)
  (println sentance)
  (println words)
  ;; (println @word_freq)
  (doseq [word words]
    (word-check @word_freq word)))