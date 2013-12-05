(defproject mandelbrot-clojure "0.1.0-SNAPSHOT"
  :description "Mandelbrot Generator"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.5.1"]]
  :main ^:skip-aot mandelbrot.generator
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
