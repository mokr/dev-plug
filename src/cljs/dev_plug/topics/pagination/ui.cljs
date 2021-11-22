(ns dev-plug.topics.pagination.ui
  (:require [re-frame.core :as rf]
            [plug-pagination.core :as pag]
            [plug-utils.re-frame :refer [<sub >evt]]
            [plug-utils.debug :as ud]
            ))



(rf/reg-sub
  ::unpaginated-content
  (fn []
    (->> (range 100)
         (map (fn [v]
                {:id    v
                 :value (str v)}))
         ;(ud/DEBUG-print "RANGE")
         )))


(rf/dispatch [::pag/register-config
              {:id                     :demo
               :allow-jump?            true
               :allow-set-per-page?    false
               :allowed-items-per-page [5 10 20 50 100 200]
               :items-per-page         10}])


(rf/reg-sub
  ::paginated-content
  :<- [::unpaginated-content]
  :<- [::pag/config :demo]
  pag/calc-pagination-for-re-frame-subscription)



;|-------------------------------------------------
;| UI


(defn list-entry [{:keys [value]}]
  [:li (str "foo-" value)])


(defn listing [entries]
  [:ul
   (for [entry entries]
     ^{:key (:id entry)}
     [list-entry entry])])


(defn paginated-listing [{:keys [content] :as pag-opts}]
  [:div
   [pag/pagination-controls (<sub [::paginated-content])]
   [listing content]])


(defn page []
  [:section.section>div.container>div.content
   [:h3 "Pagination"]
   [paginated-listing (<sub [::paginated-content])]])


;|-------------------------------------------------
;| REPL

(comment
  (<sub [::paginated-content])
  )