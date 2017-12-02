(ns duct.middleware.buddy-test
  (:require [clojure.test :refer :all]
            [duct.middleware.buddy :as buddy]
            [integrant.core :as ig]
            [ring.mock.request :as mock]))

(defn- test-authfn [_ {:keys [username password]}]
  username)

(defn- auth-identity-handler [{:keys [identity]}]
  {:status 200, :headers {}, :body identity})

(deftest test-authentication
  (let [middleware (ig/init-key ::buddy/authentication
                                {:backend :basic
                                 :realm   "Test"
                                 :authfn  test-authfn})
        handler    (middleware auth-identity-handler)]
    (is (= (handler (-> (mock/request :get "/")
                        (mock/header "authorization"
                                     "Basic QWxhZGRpbjpPcGVuU2VzYW1l")))
           {:status 200, :headers {}, :body "Aladdin"}))
    (is (= (handler (mock/request :get "/"))
           {:status 200, :headers {}, :body nil}))))
