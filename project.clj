(defproject duct/middleware.buddy "0.2.0"
  :description "Duct library for Buddy middleware"
  :url "https://github.com/duct-framework/middleware.buddy"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.10.1"]
                 [buddy/buddy-auth "2.2.0"]
                 [duct/core "0.8.0"]
                 [integrant "0.8.0"]]
  :profiles
  {:dev {:dependencies [[ring/ring-mock "0.4.0"]]}})
