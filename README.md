# REBL with Lein REPL example

A simple example of how to setup [REBL](https://github.com/cognitect-labs/REBL-distro) to run within `lein repl`.

## Installation

1. First [download REBL](http://rebl.cognitect.com/download.html)
2. Extract the REBL jar file and move it into resources/REBL-xxxx.jar.
3. Update your project.clj file to use Clojure 1.10.0-RC2 or higher, core async, and add the REBL jar to your resource paths:

    ```clj
    (defproject rebl-test "0.1.0-SNAPSHOT"
      :description "FIXME: write description"
      :url "http://example.com/FIXME"
      :dependencies [[org.clojure/clojure "1.10.0-RC2"]
                     [org.clojure/core.async "0.4.490"]]
      :resource-paths ["resources/REBL-0.9.108.jar"]
      :main ^:skip-aot rebl-test.core
      :target-path "target/%s"
      :profiles {:uberjar {:aot :all}})
    ```
4. In your terminal run `lein repl` to start an nrepl server and client.
5. Once the Leiningen repl loads, run the following:

    ```clj
    (require '[cognitect.rebl :as rebl])
    (rebl/ui)
    ```

    Example:

    ```clj
    nREPL server started on port 57844 on host 127.0.0.1 - nrepl://127.0.0.1:57844
    REPL-y 0.3.7, nREPL 0.2.12
    Clojure 1.10.0-RC2
    Java HotSpot(TM) 64-Bit Server VM 1.8.0_144-b01
        Docs: (doc function-name-here)
              (find-doc "part-of-name-here")
      Source: (source function-name-here)
     Javadoc: (javadoc java-object-or-class-here)
        Exit: Control+D or (exit) or (quit)
     Results: Stored in vars *1, *2, *3, an exception in *e

    rebl-test.core=> (require '[cognitect.rebl :as rebl])
    nil
    rebl-test.core=> (rebl/ui)
    nil
    ```

The REBL UI should display and you are good to go :smile: :beer:

---

## Alternate Installation & Configuration Approaches

### Dev Profile

You may want to only include access to the REBL jar while in development. Below is one of many ways to achieve this.

```clj
(defproject rebl-test "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :dependencies [[org.clojure/clojure "1.9.0"]]
  :main ^:skip-aot rebl-test.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}
             :dev [:project/dev :profiles/dev]
             :project/dev {:dependencies [[org.clojure/clojure "1.10.0-RC2"]
                                          [org.clojure/core.async "0.4.490"]]
                           :resource-paths ["env/dev/resources"]}})
```

> **TIP**: _If you replace :dev profile with :repl the configuration only applies when running `lein.repl`. Read the [lein profiles](https://github.com/technomancy/leiningen/blob/master/doc/PROFILES.md) docs for more configuration options._

### Lein Local Repo

1. Add [lein-localrepo](https://github.com/kumarshantanu/lein-localrepo) plugin to profiles.clj
2. Download REBL and extract the zip.
3. Install the JAR as a shared maven repo by running this command relative to the jar:
   ```shell
   lein localrepo install REBL-0.9.108.jar com.cognitect/rebl 0.9.108
   ```
4. Now you can add it to your dependencies:
   ```clj [com.cognitect/rebl "0.9.108"]```
