(ns leiningen.field
	(:require 
		[clojure.pprint :as pprint]
		[leiningen.classpath :as classpath]
		[me.raynes.conch.low-level :as sh]
		[environ.core :as env]))

(defn field
	"Launch Field inside this project. Optionally takes name of Field sheet to open"
	([project]
	(let
		[cp (classpath/get-classpath-string project)
		 fr (env/env :field)
		 proc (apply sh/proc "/bin/bash" fr "-useGit" "1" "-extendedJars" cp "-versioning.dir" (format "%s/field" (project :root)) (project :field-arguments []))
		 f (future (sh/stream-to-out proc :out))]
		(do 
			(println (sh/exit-code proc))
			(println (env/env :field-root)))))
	([project doc]
	(let
		[cp (classpath/get-classpath-string project)
		 fr (env/env :field)
		 proc (sh/proc "/bin/bash" fr "-useGit" "1" "-extendedJars" cp "-versioning.dir" (format "%s/field" (project :root)) "-field.scratch" (if (.endsWith doc ".field") doc (str doc ".field")))
		 f (future (sh/stream-to-out proc :out))]
		(do 
			(println (sh/exit-code proc))
			(println (env/env :field-root))))
))

