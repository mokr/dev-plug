(ns dev-plug.routes.home
  (:require
    [dev-plug.layout :as layout]
    [clojure.java.io :as io]
    [dev-plug.middleware :as middleware]
    [ring.util.response]
    [ring.util.http-response :as response]
    [muuntaja.middleware :as muuntaja]
    ))


(defn home-page [request]
  (layout/render request "home.html"))


(defn home-routes []
  [""
   {:middleware [middleware/wrap-csrf
                 middleware/wrap-formats]}
   ["/" {:get home-page}]
   ["/api" {:middleware [
                         muuntaja/wrap-format
                         ;muuntaja/wrap-format-response
                         ]}
    ["/samples"
     ["/text" {:get (fn [req]
                      (response/ok "Hello world!"))}]
     ["/empty" {:get (fn [req]
                       (response/ok))}]
     ["/map" {:get (fn [req]
                     (response/ok {:foo  :bar
                                   "foo" "bar"
                                   1     2}))}]
     ["/map2" {:get (fn [req]
                      (response/ok {:hello/world :i.have.long/namespace!}))}]
     ["/map3" {:get (fn [req]
                      (response/ok {[:a :b :c] {1 2 3 4}
                                    #{:x}      :y}))}]]]])
