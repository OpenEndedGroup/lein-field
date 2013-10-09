(flatten (map identity
              (merge {:A 1} {:B 2})))

(merge {:A 2} {:A 1})


(hash-map 1 2 3 4)

(name :A)



(defn grooble [& args]
  (if (odd? (count args))
    (merge (apply hash-map (drop-last args))
           {:magic (last args)})
    (apply hash-map args)))

(grooble :A 1 :B 2 5)

(grooble 1)


(last [1 2 3])


(grooble)

(keyword (name ":A"))

(partition 2 [1 2 3 4 5 6])

(clojure.string/replace ":AA:A:A" #"^:" "")

(leiningen.field/command-args-map [":A" "1" ":B" "2"])

(identity (partition 2 [":A" "1" ":B" "2"]))

(apply hash-map (flatten [[:A 1] [:B 2]]))
