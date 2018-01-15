(defproject duct/middleware.buddy "0.1.0"
  :description "Duct library for Buddy middleware"
  :url "https://github.com/duct-framework/middleware.buddy"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.9.0"]
                 [buddy/buddy-auth "2.1.0"]
                 [duct/core "0.6.2"]
                 [integrant "0.6.3"]]
  :profiles
  {:dev {:dependencies [[ring/ring-mock "0.3.2"]]}})
