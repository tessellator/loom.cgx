(ns loom.cgx.convert
  (:require [loom.graph :as lg]
            [loom.label :as ll]
            [loom.cgx.graph :as cgx]))

(defn relation-tuples [graph-node]
  (let [concepts (cgx/concepts graph-node)
        relations (cgx/relations graph-node)
        arrows (cgx/arrows graph-node)]
    (keep identity
          (for [{xfrom :from xto :to} arrows
                {yfrom :from yto :to} arrows
                :when (= xto yfrom)]
            (let [from (get concepts xfrom)
                  to (get concepts yto)]
              (when (and from to)
                {:edge [from to]
                 :relation (get relations xto)}))))))

(defn add-relation-tuple [digraph {:keys [edge relation]}]
  (-> digraph
      (lg/add-edges edge)
      (ll/add-label edge relation)))

(defn add-relations [digraph graph-node]
  (reduce add-relation-tuple
          digraph
          (relation-tuples graph-node)))

(defn add-concept [digraph concept-node]
  (lg/add-nodes digraph
                (cgx/concept-label concept-node)))

(defn add-concepts [digraph graph-node]
  (reduce add-concept
          digraph
          (cgx/concept-nodes graph-node)))

(defn cgx->loom [cgx]
  (let [graph-node (cgx/graph-node cgx)]
    (-> (lg/digraph)
        (add-concepts graph-node)
        (add-relations graph-node))))
