;HW10
(defn constant [a] (constantly a))
(defn variable [a] (fn [args] (args a)))

(defn operation [func]
      (fn [& operations] (fn [& args]
              (apply func (map #(apply % args) operations)))))

(def add (operation +))
(def subtract (operation -))
(def multiply (operation *))
(def divide (operation #(/ (double %1) (double %2))))
(def negate (operation -))
(def exp (operation #(Math/exp %1)))
(def ln (operation #(Math/log %1)))
(def arcTan (operation #(Math/atan %1)))
(def arcTan2 (operation #(Math/atan2 %1 %2)))

(def OPERATIONS
  {'+ add
   '- subtract
   '* multiply
   '/ divide
   'negate negate
   'exp exp
   'ln ln
   'atan arcTan
   'atan2 arcTan2})

(defn parse [expr]
      (cond (symbol? expr) (variable (name expr))
            (number? expr) (constant expr)
        :else (apply (OPERATIONS (first expr)) (map parse (rest expr)))))

(defn parseFunction [a]
      (->> a read-string parse))
;HW11
(defn prototypes [prt a]
      (if-let [val (get prt a)]
              val (when-let [pr (get prt :prototype)]
                            (prototypes pr a))))
(defn prot [a] #(prototypes % a))
(defn method [a]
      (fn [this & args]
          (when-let [method-fn (prototypes this a)]
                    (apply method-fn this args))))
(def toString (method :toString))
(def evaluate (method :evaluate))
(def operands (prot :operands))
(def ConstProt
  {:toString (fn [this] (format "%.1f" (:value this)))
   :evaluate :value})
(defn Constant [a]
      {:prototype ConstProt
       :value a})
(def VarProt
  {:toString (fn [this] (:value this))
   :evaluate (fn [this a] (a (:value this)))})
(defn Variable [a]
      {:prototype VarProt
       :value a})
(defn oper [this a]
      (map #(apply % [a])
           (map #(-> % (method :df) (%))
                (operands this))))
(def opertions
  (let [symbol (prot :ind) df (method :df) function (prot :step)]
       {:toString (fn [this]
                      (str "(" (symbol this) " " (clojure.string/join " " (mapv toString (:operands this))) ")"))
        :evaluate (fn [this a]
                      (apply (function this) (mapv #(evaluate % a) (:operands this))))}))
(defn opertion [ind step df]
      (fn [& operands]
          {:operands (vec operands) :prototype {:prototype opertions :ind ind :step step}}))
(def Add (opertion '+ + (fn [] (apply Add (oper)))))
(def Subtract (opertion '- - (fn [] (apply Subtract (oper)))))
(def Negate (opertion 'negate - (fn [] (apply Negate (oper)))))
(def Multiply
  (opertion '* * (fn [this a] (Add (Multiply (oper) (method (oper this 1)))
                                             (Multiply (oper this 1) (method (oper)))))))
(def Divide
  (opertion '/ (fn [x y] (/ x (double y)))
                    (fn [this a] (Divide (Subtract (Multiply (oper this 1) (method (oper)))
                                                     (Multiply (oper) (method (oper this 1))))))))
(def Cos)
(def Sin (opertion 'sin (fn [x] (Math/sin x)) (fn [] (Multiply(Cos(oper))))))
(def Cos (opertion 'cos (fn [x] (Math/cos x)) (fn [] (Multiply(Sin(oper))))))
(def Cosh)
(def Sinh (opertion 'sinh (fn [x] (Math/sinh x)) (fn [] (Multiply(Cosh(oper))))))
(def Cosh (opertion 'cosh (fn [x] (Math/cosh x)) (fn [] (Multiply(Sinh(oper))))))
(def OPERATION
  {'+      Add
   '-      Subtract
   '*      Multiply
   '/      Divide
   'negate Negate
   'sin    Sin
   'cos    Cos
   'sinh   Sinh
   'cosh   Cosh})
(defn parser [expr]
      (cond (symbol? expr) (Variable (name expr))
            (number? expr) (Constant expr)
        :else (apply (OPERATION (first expr)) (map parser (rest expr)))))
(defn parseObject [a] (parser (read-string a)))