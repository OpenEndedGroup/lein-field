(ns leiningen.field
  (:require [clojure.pprint :as pprint]
            [leiningen.classpath :as classpath]
            [me.raynes.conch.low-level :as sh]
            [environ.core :as env]))

(defn field
  "Launch Field inside this project. Optionally takes name of Field sheet to open."
  ([project doc]
     (let [cp (classpath/get-classpath-string project)
           fr (env/env :field)
           defaults {"-useGit" 1
                     "-extendedJars" cp
                     "-versioning.dir" (format "%s/field" (project :root))}
           project-field-args (apply hash-map (project :field-arguments []))
           sheet-arg (when doc {"-field.scratch"
                                (if (.endsWith doc ".field") doc (str doc ".field"))})
           all-args (flatten (map (fn [[k v]] [k (str v)])
                                  (merge defaults project-field-args sheet-arg)))
           proc (apply sh/proc "/bin/bash" fr all-args)
           f (future (sh/stream-to-out proc :out))]
       (println (sh/exit-code proc))
       (println (env/env :field-root))))

  ([project]
     (field project nil)))
