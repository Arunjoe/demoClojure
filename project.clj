(defproject demoapp "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[org.clojure/clojure "1.10.3"]
                 [lt.tokenmill/timewords "0.5.0"]
                 [com.novemberain/monger "3.1.0"]
                 [org.clojure/tools.logging "1.1.0"]

                ;;  [io.pedestal/pedestal.service "0.5.9"]
                 [clj-pdf "2.5.8"]
                 [csv-to-json "0.1.2-SNAPSHOT"]
                 [org.clojure/data.csv  "0.1.2"]
                 [org.clojure/data.json "0.2.3"]]
  :aot :all
  :main ^:skip-aot demoapp.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all
                       :jvm-opts ["-Dclojure.compiler.direct-linking=true"]}})
