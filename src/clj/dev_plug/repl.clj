(ns dev-plug.repl
  "Backend REPL work"
  (:require [plug-link.core :as link]))



(comment
  (link/send! [:first/msg "Hello client!"])
  (link/broadcast! [:first/msg "Hello clients!"])
  )