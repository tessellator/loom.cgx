# loom.cgx

A Clojure library that parses a CGX file (e.g., from
[CharGer](http://charger.sourceforge.net)) into a
[loom](https://github.com/aysylu/loom) digraph.

## Releases and Dependency Information

Latest stable release: 0.1.0

[deps.edn](https://clojure.org/guides/deps_and_cli) dependency information:

    tessellator/loom.cgx {:mvn/version "0.1.0"}

[Leiningen](https://github.com/technomancy/leiningen) dependency information:

    [tessellator/loom.cgx "0.1.0"]

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
