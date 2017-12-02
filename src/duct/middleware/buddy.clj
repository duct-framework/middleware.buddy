(ns duct.middleware.buddy
  (:require [buddy.auth.middleware :as buddy]
            [buddy.auth.backends :as backend]
            [integrant.core :as ig]))

(def backends
  {:basic backend/basic})

(defmethod ig/init-key :duct.middleware.buddy/authentication
  [_ {:keys [backend] :as config}]
  (let [backend-fn (backends backend)
        config     (dissoc config backend)]
    (fn [handler]
      (buddy/wrap-authentication handler (backend-fn config)))))
