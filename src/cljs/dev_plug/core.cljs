(ns dev-plug.core
  (:require
    [day8.re-frame.http-fx]
    [reagent.dom :as rdom]
    [reagent.core :as r]
    [re-frame.core :as rf]
    [goog.events :as events]
    [goog.history.EventType :as HistoryEventType]
    [markdown.core :refer [md->html]]
    [dev-plug.ajax :as ajax]
    [dev-plug.events]
    [dev-plug.topics.fetch.core :as fetch]
    [dev-plug.topics.pagination.ui :as pagination]
    [dev-plug.topics.ui.core :as ui]
    [plug-link.core :as link]
    [plug-link.re-frame]
    [plug-ui.bulma.notifications :as notifications]
    [plug-utils.reagent :as ur]
    [reitit.core :as reitit]
    [reitit.frontend.easy :as rfe]
    [clojure.string :as string])
  (:import goog.History))

(defn nav-link [uri title page]
  [:a.navbar-item
   {:href  uri
    :class (when (= page @(rf/subscribe [:common/page-id])) :is-active)}
   title])

(defn navbar []
  (r/with-let [expanded? (r/atom false)]
              [:nav.navbar.is-info>div.container
               [:div.navbar-brand
                [:a.navbar-item {:href "/" :style {:font-weight :bold}} "dev-plug"]
                [:span.navbar-burger.burger
                 {:data-target :nav-menu
                  :on-click    #(swap! expanded? not)
                  :class       (when @expanded? :is-active)}
                 [:span] [:span] [:span]]]
               [:div#nav-menu.navbar-menu
                {:class (when @expanded? :is-active)}
                [:div.navbar-start
                 [nav-link "#/" "Home" :home]
                 [nav-link "#/fetch" "Fetch" :fetch]
                 [nav-link "#/ui" "UI" :ui]
                 [nav-link "#/pagination" "Pagination" :pagination]
                 [nav-link "#/about" "About" :about]]]]))

(defn about-page []
  [:section.section>div.container>div.content
   [:img {:src "/img/warning_clojure.png"}]])

(defn home-page []
  [:section.section>div.container>div.content

   ])

(defn page []
  (when-let [page @(rf/subscribe [:common/page])]
    [:div
     ;[ur/err-boundary [notifications/panel :style {:top "50px"}]] ;; Note: The :style part is optional, but allows adjusting placement
     [ur/err-boundary [notifications/panel]]
     [navbar]
     [page]]))


(defn navigate! [match _]
  (rf/dispatch [:common/navigate match]))


(def router
  (reitit/router
    [["/" {:name        :home
           :view        #'home-page
           :controllers [{:start (fn [_] (rf/dispatch [:page/init-home]))}]}]
     ["/about" {:name :about
                :view #'about-page}]
     ["/fetch" {:name :fetch
                :view #'fetch/page}]
     ["/pagination" {:name :pagination
                     :view #'pagination/page}]
     ["/ui" {:name :ui
             :view #'ui/page}]]))


(defn start-router! []
  (rfe/start!
    router
    navigate!
    {}))

;; -------------------------
;; Initialize app
(defn ^:dev/after-load mount-components []
  (rf/clear-subscription-cache!)
  (rdom/render [#'page] (.getElementById js/document "app")))

(defn init! []
  (start-router!)
  (ajax/load-interceptors!)
  (link/init)
  (mount-components))
