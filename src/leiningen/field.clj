(ns leiningen.field
  (:require [clojure.pprint :as pprint]
            [leiningen.classpath :as classpath]
            [me.raynes.conch.low-level :as sh]
            [environ.core :as env]))

(defn- command-args-map
  "Take leading ':' from keyword tokens in args, then actually keywordize them."
  [args]
  (apply hash-map (flatten (map (fn [[k v]] [(keyword (clojure.string/replace k #"^:" "")) v])
                                (partition 2 args)))))

(defn- command-line-to-map
  "Handle :A foo :B bar :C baz sheetname (optional '.field')"
  [args]
  (if (odd? (count args))
    (let [sheet-name (last args)]
      (merge (command-args-map (drop-last args))
             {:field.scratch (if (.endsWith sheet-name ".field") sheet-name (format "%s.field" sheet-name))}))
    (command-args-map args)))

(defn field
  "Launch Field inside this project. Optionally takes name of Field sheet to open."
  [project & args]
  (let [cp (classpath/get-classpath-string project)
        fr (env/env :field)
        defaults {:useGit 1
                  :extendedJars cp
                  :versioning.dir (format "%s/field" (project :root))}
        project-field-args (project :field-arguments nil)
        command-line-args (command-line-to-map args)
        all-args (flatten (map (fn [[k v]] [(str "-" (name k)) (str v)])
                               (merge defaults project-field-args command-line-args)))
        _ (println all-args)
        proc (apply sh/proc "/bin/bash" fr all-args)
        f (future (sh/stream-to-out proc :out))]
    (println (sh/exit-code proc))
    (println (env/env :field-root))))
