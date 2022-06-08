(ns dev-plug.topics.ui.core
  "Demo plug-ui components"
  (:require [dev-plug.topics.ui.notifications :as notifications]))


(defn page []
  [:section.section>div.container>div.content
   [:h1 "plug-ui"]
   [notifications/panel]

   ]
  )