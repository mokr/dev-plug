# dev-plug

A dev/demo project for local development of plug-* libraries. Uses `:local/root` of `tools.deps` to access such
libraries living in the same folder as this one.

Status:

* Experimental
* Work in progress

# GOTCHAS

### "Errors during project resolution" (when using :local/root)

> The following errors were found during project resolve:
> /Users/mokr/code/opensource/dev-plug/deps.edn:
> Manifest type not detected when finding deps for net.clojars.mokr/plug-ui
> in coordinate {:local/root "/Users/mokr/code/opensource/plug-ui"}

**Remedy:** Run `lein pom` in the `:local/root` project it complains about (`plug-ui` in example above)

# DEVELOPMENT

### Prerequisites

    npm install         # once + after updates to package.json
    brew install clojure/tools/clojure
    npm install -g shadow-cljs

#### In a :root/local lein project with changed dependencies

    lein pom

### Terminals

Terminal 1 - Run backend

    clojure -M:dev

Terminal 2 - Compile frontend with reloading

    shadow-cljs watch app

### URLs

URL | Terminal | Description
--- |---       |---
[http://localhost:8000](http://localhost:3000) | 1 | Web app
[http://localhost:9630](http://localhost:9630) | 2 | Shadow-cljs server status. Included when running "shadow-cljs"

### REPLs

Port | Description    | Notes
---  |---             |--- 
9000 | Backend nREPL  |
9002 | Frontend nREPL | 1) Open app in browser. 2) Run in REPL: `(shadow/repl :app)`

_Note: On other ports than Luminus typically uses to be able to run alongside one_

# GENERAL

generated using Luminus version "4.19"

```shell
lein new luminus dev-plug +re-frame +shadow-cljs
```

## LICENSE

Copyright © 2021 Morten Kristoffersen

This program and the accompanying materials are made available under the terms of the Eclipse Public License 2.0 which
is available at
http://www.eclipse.org/legal/epl-2.0.

This Source Code may also be made available under the following Secondary Licenses when the conditions for such
availability set forth in the Eclipse Public License, v. 2.0 are satisfied: GNU General Public License as published by
the Free Software Foundation, either version 2 of the License, or (at your option) any later version, with the GNU
Classpath Exception which is available at https://www.gnu.org/software/classpath/license.html.
