(defproject rebl-test "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :dependencies [[org.clojure/clojure "1.10.0-RC2"]
                 [org.clojure/core.async "0.4.490"]]
  :resource-paths ["resources/REBL-0.9.108.jar"]
  :main ^:skip-aot rebl-test.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
