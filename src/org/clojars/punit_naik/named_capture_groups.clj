(ns org.clojars.punit-naik.named-capture-groups)

(defn capture-group-names
  "If you have a regex with named capture groups like:
   `#\"(?<year>\\d+)\\-(?<month>\\d+)\\-(?<day>\\d+)\"`
   This function will return a list like: `[\"year\", \"month\", \"day\"]`"
  [regex]
  (let [matcher (re-matcher #"\<(\w+)\>" (str regex))]
    (loop [result []]
      (let [[_ group-name :as match] (re-find matcher)]
        (if-not (seq match)
          result
          (recur (conj result group-name)))))))

(defn named-capture-groups
  "If your regex has named capture groups
   This function will return a map of `<group-name>:<matched-string>`"
  [regex input]
  (let [matcher (re-matcher regex input)]
    (when (.matches matcher)
      (->> (capture-group-names regex)
           (map (fn [n] [(keyword n) (.group matcher n)]))
           (into {})))))
