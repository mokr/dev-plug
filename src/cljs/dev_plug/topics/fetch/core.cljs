(ns dev-plug.topics.fetch.core
  (:require [re-frame.core :as rf]
            [plug-fetch.core :as fetch]
            [plug-utils.re-frame :refer [<sub >evt]]))

(def ^:private SAMPLES :data/samples)


(rf/reg-event-fx
  :fetch/sample
  [rf/trim-v]
  (fn [{:keys [db]} [type]]
    (let [uri (str "/api/samples/" (name type))]
      (fetch/make-fx-map-for-backend-event
        {:method      :get
         :uri         uri
         :result-path [SAMPLES type]}))))


(rf/reg-sub
  :all/samples
  (fn [db [_]]
    (get-in db [SAMPLES type] "No data yet")
    (or
      (with-out-str (cljs.pprint/pprint (get db SAMPLES)))
      "No data yet")))


(defn fetch-sample-button [type]
  [:div.mb-1
   [:button.button.is-primary {:on-click #(>evt [:fetch/sample type])}
    ;(str "Sample " (name type))
    (name type)]])


(defn page []
  [:section.section>div.container>div.content
   [:div.columns
    [:div.column.is-narrow
     [:strong "Sample"]
     [fetch-sample-button :text]
     [fetch-sample-button :empty]
     [fetch-sample-button :map]
     [fetch-sample-button :map2]
     [fetch-sample-button :map3]
     ]
    [:div.column
     [:div [:pre>code (<sub [:all/samples])]]]
    ]])
