(ns dev-plug.env
  (:require
    [selmer.parser :as parser]
    [clojure.tools.logging :as log]
    [dev-plug.dev-middleware :refer [wrap-dev]]))

(def defaults
  {:init
               (fn []
                 (parser/cache-off!)
                 (log/info "\n-=[dev-plug started successfully using the development profile]=-"))
   :stop
               (fn []
                 (log/info "\n-=[dev-plug has shut down successfully]=-"))
   :middleware wrap-dev})
