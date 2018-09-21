# loom.cgx

A Clojure library that parses a CGX file (e.g., from
[CharGer](http://charger.sourceforge.net)) into a
[loom](https://github.com/aysylu/loom) digraph.

## Usage

There is a sample CGX file in the `doc` directory that
can be used for testing.

```clojure
(require '[loom.cgx :as cgx])
(require '[loom.io :as lio])

(def cg (cgx/parse-cgx "/path/to/file.cgx"))
(lio/view cg)
```

## License

Copyright © 2018 Thomas C. Taylor

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
