(ns dev-plug.topics.ui.repl
  "Convenience repl ns to have some essentials available for repl evaluation"
  (:require [plug-utils.re-frame :refer [<sub >evt]]
            [dev-plug.topics.ui.notifications :as notifications]))


;|-------------------------------------------------
;| NOTIFICATIONS

(comment
  (>evt [:new/notification {:severity (rand-nth [:info :warn :error :invalid])
                            :text     "Fail!"}])

  (>evt [:reg/error {:source  :my-source
                     :action  :fetch/users
                     :message "Failed fetching users"
                     :raw     "RAW failed fetching users"}])

  (let [dummy-notifications [{:severity :error :text "Fetching adapterlog failed!"}
                             {:severity :warn :text "Something happened during processing.\nNew line with info"}
                             {:severity :info :text "Done processing"}]]
    (doseq [notification dummy-notifications]
      (>evt [:new/notification notification])))

  (>evt [:new/notification {:severity :error :text "Fetching adapterlog failed!"}])
  (>evt [:new/notification {:severity :warn :text "Something happened during processing.\nNew line with info"}])
  (>evt [:new/notification {:severity :info :text "Done processing"}])

  (make-notification {:severity :error :text "Fetching adapterlog failed!"} (ut/epoch-millis-now) #'notifications/ttl)

  )