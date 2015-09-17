(ns ragtime-cli-tools.core
  (:require
   [clojure.edn :as edn]
   [clojure.java.jdbc :refer [with-db-connection with-db-transaction]]
   [ragtime.jdbc :as jdbc]
   [ragtime.repl :as repl]))

(defn jdbc-spec [env]
  {:dbtype      (env :db-type)
   :dbname      (env :db-name)
   :username    (env :db-username)
   :password    (env :db-password)
   :host        (env :db-host)
   :port        (edn/read-string (env :db-port))})

(defn migrator [db]
  {:datastore  (jdbc/sql-database db)
   :migrations (jdbc/load-resources "migrations")})

(defn migrate [env]
  (with-db-connection [db (jdbc-spec env)]
    (repl/migrate (migrator db))))

(defn rollback [env]
  (with-db-connection [db (jdbc-spec env)]
    (repl/rollback (migrator db))))
