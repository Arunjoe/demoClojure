(ns demoapp.catsndogs)

(defn word-check[sent word]
  (def freq (frequencies sent))
  (doseq [w word]
  (println w)
    (def left_occ (get freq w))
    (if (or (not= nil left_occ)(not= 0 left_occ))
      (do
        (println ">>" left_occ)
        ;; (update freq w dec)
        (assoc freq w dec)
        (print freq)
        (println "updated --" (get freq w))
        )
      )
    )
  )

(defn catsdogs [sentance & words]
  (println sentance)
  (println words)
  (doseq [word words]
     (word-check sentance word)
    (println "---" word))
  )