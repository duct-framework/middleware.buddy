(ns duct.middleware.buddy-test
  (:require [buddy.sign.jwt :as jwt]
            [clojure.test :refer :all]
            [duct.middleware.buddy :as buddy]
            [integrant.core :as ig]
            [ring.mock.request :as mock]))

(defn- test-basic-authfn [_ {:keys [username password]}] username)
(defn- test-token-authfn [_ token] token)

(defn- auth-identity-handler [{:keys [identity]}]
  {:status 200, :headers {}, :body identity})

(deftest test-authentication
  (testing "basic authentication"
    (let [middleware (ig/init-key ::buddy/authentication
                                  {:backend :basic
                                   :realm   "Test"
                                   :authfn  test-basic-authfn})
          handler    (middleware auth-identity-handler)]
      (is (= {:status 200, :headers {}, :body "Aladdin"}
             (handler (-> (mock/request :get "/")
                          (mock/header "authorization"
                                       "Basic QWxhZGRpbjpPcGVuU2VzYW1l")))))
      (is (= {:status 200, :headers {}, :body nil}
             (handler (mock/request :get "/"))))))

  (testing "token authentication"
    (let [middleware (ig/init-key ::buddy/authentication
                                  {:backend :token
                                   :authfn  test-token-authfn})
          handler    (middleware auth-identity-handler)]
      (is (= {:status 200, :headers {}, :body "abcedef0123456789"}
             (handler (-> (mock/request :get "/")
                          (mock/header "authorization"
                                       "Token abcedef0123456789")))))
      (is (= {:status 200, :headers {}, :body nil}
             (handler (mock/request :get "/"))))))

  (testing "session authentication"
    (let [middleware (ig/init-key ::buddy/authentication {:backend :session})
          handler    (middleware auth-identity-handler)]
      (is (= {:status 200, :headers {}, :body "Alice"}
             (handler (-> (mock/request :get "/")
                          (assoc-in [:session :identity] "Alice")))))
      (is (= {:status 200, :headers {}, :body nil}
             (handler (mock/request :get "/"))))))

  (testing "jws authentication"
    (let [middleware (ig/init-key ::buddy/authentication
                                  {:backend :jws
                                   :secret  "secret"})
          token      (jwt/sign {:user "Bob"} "secret")
          handler    (middleware auth-identity-handler)]
      (is (= {:status 200, :headers {}, :body {:user "Bob"}}
             (handler (-> (mock/request :get "/")
                          (mock/header "authorization" (str "Token " token))))))
      (is (= {:status 200, :headers {}, :body nil}
             (handler (mock/request :get "/"))))))

  (testing "jws authentication"
    (let [secret     "secretsecretsecretsecretsecretse"
          middleware (ig/init-key ::buddy/authentication
                                  {:backend :jwe
                                   :secret  secret})
          token      (jwt/encrypt {:user "Carol"} secret)
          handler    (middleware auth-identity-handler)]
      (is (= {:status 200, :headers {}, :body {:user "Carol"}}
             (handler (-> (mock/request :get "/")
                          (mock/header "authorization" (str "Token " token))))))
      (is (= {:status 200, :headers {}, :body nil}
             (handler (mock/request :get "/")))))))
