# ragtime-cli-tools

A small library to apply database migrations using the ragtime library

## Installation

`[ragtime-cli-tools "1.0.0"]`

## Usage

```clojure
(ns my-app.core
  (:require
    [environ.core :refer [env]]
    [ragtime-cli-tools.core :as db-cli]))

(def -main [& args]
  (let [sub-cmd (first args)]
    (case sub-cmd
      "migrate" (db-cli/migrate env)
      "rollback" (db-cli/rollback env)
      ;; application logic goes here....
      )))
```

## License

Copyright © 2015 Funding Circle.

Distributed under the BSD 3-Clause License.
