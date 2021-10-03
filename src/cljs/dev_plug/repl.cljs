(ns dev-plug.repl
  "Frontend REPL work"
  (:require [plug-link.core :as link]))



(comment
  (link/send! [:first/msg "Hello server!"])
  )