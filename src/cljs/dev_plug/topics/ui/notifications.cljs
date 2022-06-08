(ns dev-plug.topics.ui.notifications
  "Demo plug-ui notifications.
  Note: The plug-ui notifications panel itself is included as part of main page in core ns"
  (:require [plug-utils.re-frame :refer [<sub >evt]]
            [plug-utils.time :as ut]))


;|-------------------------------------------------
;| HELPERS

;;TODO: Mixed :reg/error with :new/notification. Maybe align fields more anyway (e.g. :text as :message?
(defn- random-notification []
  (let [severity (rand-nth [:error :warn :info])
        action   (rand-nth [:create/user :delete/user :update/user :get/user])
        ;source   (rand-nth [:src/a :src/b :src/c :src/d])
        text     (str "Message generated at " (ut/time-now-local-str-with-millis)
                      (repeat 30 "abc"))
        raw      (str "RAW: " text)]
    {:severity severity
     :text     text}))


;|-------------------------------------------------
;| UI

(defn trigger-random-notification []
  [:button.button.is-info {:on-click #(>evt [:new/notification (random-notification)])}
   "Trigger random notification"])


(defn panel []
  [:div
   [:div.level
    [:div.level-left
     [:div.level-item "Text to the left"]]
    [:div.level-right
     [:div.level-item "Text to the right"]]]
   [trigger-random-notification]])