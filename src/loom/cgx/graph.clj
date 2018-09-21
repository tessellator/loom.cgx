(ns loom.cgx.graph
  (:require [clojure.xml :as xml]
            [com.rpl.specter :refer :all]))

;; -----------------------------------------------------------------------------
;; Generic node values

(defn id [node]
  (get-in node [:attrs :id]))

(defn type-label [type-node]
  (get-in type-node [:content 0 :content 0]))

(defn find-type-label [node]
  (-> (select-one [ALL #(= :type (:tag %))]
                  (:content node))
      type-label))

;; -----------------------------------------------------------------------------
;; Concepts

(defn concept-id [concept-node]
  (get-in concept-node [:attrs :id]))

(defn concept-label [concept-node]
  (find-type-label concept-node))

(defn concept-nodes [graph-node]
  (select [ALL (pred #(= :concept (:tag %)))]
          (:content graph-node)))

(defn concepts [graph-node]
  (reduce #(assoc %1 (concept-id %2) (concept-label %2))
          {}
          (concept-nodes graph-node)))

;; -----------------------------------------------------------------------------
;; Relations

(defn arrow-start [arrow-node]
  (get-in arrow-node [:attrs :from]))

(defn arrow-end [arrow-node]
  (get-in arrow-node [:attrs :to]))

(defn arrow-nodes [graph-node]
  (select [ALL (pred #(= :arrow (:tag %)))]
          (:content graph-node)))

(defn arrows [graph-node]
  (map #(hash-map :from (arrow-start %)
                  :to (arrow-end %))
       (arrow-nodes graph-node)))

(defn relation-nodes [graph-node]
  (select [ALL (pred #(= :relation (:tag %)))]
          (:content graph-node)))

(defn relations [graph-node]
  (reduce #(assoc %1 (id %2) (find-type-label %2))
          {}
          (relation-nodes graph-node)))

;; -----------------------------------------------------------------------------
;; Graphs

(defn graph-node [cgx]
  (first (:content cgx)))

;; -----------------------------------------------------------------------------
;; I/O

(defn parse-cgx [path-to-cgx]
  (xml/parse path-to-cgx))
