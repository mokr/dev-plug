(ns dev-plug.app
  (:require [dev-plug.core :as core]))

;;ignore println statements in prod
(set! *print-fn* (fn [& _]))

(core/init!)
