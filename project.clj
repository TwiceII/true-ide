(defproject true-ide "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.9.0-alpha13"]
                 [proto-repl "0.3.1"]
                 [philoskim/debux "0.3.7"]]
  :main ^:skip-aot true-ide.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
