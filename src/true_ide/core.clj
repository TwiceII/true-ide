(ns true-ide.core
  (:gen-class))


; (dbx/dbgn (defn get-op
;             [mp]
;             (if (= (:op mp) :add)
;               +
;               -)))
(defn get-op
  [mp]
  (if (= (:op mp) :add)
    +
    -))

(defn calc
  [x y m]
  ((get-op m) x y))


; (defmacro printing
;   [func]
;   (let [[d name args body] func]
;     `(defn ~(symbol (str name "-repl"))
;        ~args
;        (do
;          (println "hey there!")
;          ~@(for [a args]
;              `(println ~(str "" a "=> ") ~a))
;          (let [res# ~body]
;            (println "res=> " res#)
;            res#)))))

(def infos (atom []))

(defmacro printing
  [func]
  (let [[d name args body] func]
    `(defn ~(symbol (str name ""))
       ~args
       (let [info# (sorted-map)]
         (-> info#
             (assoc :fn-ref ~name)
             (assoc :fn-name (str ~name))
             ~@(for [a args]
                `(assoc-in [:args ~(keyword a)] ~a))
             (#(let [start# (. System (nanoTime))]
                 (try
                   (let [ret# ~body
                         time# (/ (double (- (. System (nanoTime)) start#)) 1000000.0)]
                     (assoc % :ret ret# :time time#))
                   (catch Exception e# (assoc % :exception e#
                                                :time (/ (double (- (. System (nanoTime)) start#)) 1000000.0))))))
             (#(do
                 (swap! infos (fn [at#]
                                (conj at# %)))
                 (if (contains? % :exception)
                   (throw (:exception %))
                   (:ret %)))))))))


; (printing (defn calc
;            [x y m]
;            ((get-op m) x y)))
; ; ;
; (printing (defn get-op
;             [mp]
;             (if (= (:op mp) :add)
;               +
;               -)))
;
; ;
; (macroexpand-1 '(printing (defn calc
;                            [x y m]
;                            ((get-op m) x y))))
;
; (macroexpand   '(printing (defn calc
;                            [x y m]
;                            ((get-op m) x y))))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))
