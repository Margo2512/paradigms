(defn oper [f & v] (apply mapv (fn [& args] (apply f args)) v))
(defn v+ [& v] (apply oper + v))
(defn v* [& v] (apply oper * v))

(defn operat [f v1 & v2] (apply mapv (fn [& args] (apply f args)) v1 v2))
(defn v- [v1 & v2] (apply operat - v1 v2))
(defn vd [v1 & v2] (apply operat / v1 v2))
(defn m+ [m1 m2] (mapv v+ m1 m2))
(defn m- [m1 m2] (mapv v- m1 m2))
(defn m* [v1 & v2] (apply operat v* v1 v2))
(defn md [v1 & v2] (apply operat vd v1 v2))

(defn scalar [v1 v2] (reduce + (map * v1 v2)))

(defn vect [v1 v2]
      [(- (* (nth v1 1) (nth v2 2)) (* (nth v1 2) (nth v2 1)))
       (- (* (nth v1 2) (nth v2 0)) (* (nth v1 0) (nth v2 2)))
       (- (* (nth v1 0) (nth v2 1)) (* (nth v1 1) (nth v2 0)))])

(defn v*s [v s] (mapv #(* % s) v))
(defn m*s [m s] (mapv #(mapv (fn [x] (* x s)) %) m))
(defn m*v [m v] (mapv #(reduce + (map * %1 %2)) m (repeat v)))

(defn transpose [matrix] (apply mapv vector matrix))
(defn m*m [m1 m2] (let [transposed-m2 (transpose m2)] (mapv #(mapv (fn [row1]
                            (reduce + (map * row1 %))) transposed-m2) m1)))

(defn shapeless [f v1 v2] (cond (number? v1) (f v1 v2) (vector? v1) (mapv #(shapeless f %1 %2) v1 v2)))
(defn s+ [v1 v2] (shapeless + v1 v2))
(defn s- [v1 v2] (shapeless - v1 v2))
(defn s* [v1 v2] (shapeless * v1 v2))
(defn sd [v1 v2] (shapeless / v1 v2))