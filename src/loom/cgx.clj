(ns loom.cgx
  (require [loom.cgx.convert :as c]
           [loom.cgx.graph :as g]))

(defn parse-cgx
  "Parses and loads the source s, which can be a File, InputStream or
  String naming a URI. Returns a loom digraph containing the concepts
  from the CGX as node and edges between nodes labeled with with the
  name of the relation."
  [s]
  (-> s
      g/parse-cgx
      c/cgx->loom))
