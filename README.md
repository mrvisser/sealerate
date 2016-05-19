
## Sealerate

[![Build Status](https://travis-ci.org/mrvisser/sealerate.png?branch=master)](https://travis-ci.org/mrvisser/sealerate)

Sealerate is a small (3rd-party-dependency-free) library that provides a
convenience function `values[T]` that dynamically creates a set of all instances
of a sealed class.

## Set Up

Sealerate is available for both Scala 2.10 and 2.11. If you are using sbt, add
the following to your `build.sbt`:

```
libraryDependencies += "ca.mrvisser" %% "sealerate" % "0.0.4"
```

## Example Usage

```scala
package example

import ca.mrvisser.sealerate

sealed trait Switch
object Switch {
    case object On extends Switch
    case object Off extends Switch

    /**
     * Enumerate all instances of of the [[Switch]] trait
     */
    def values: Set[Switch] = sealerate.values[Switch]
}

```

Now `Switch.values` will provide `Set(On, Off)`, and will update automatically
when new objects are added.

Note that a compilation error is thrown if the following conditions aren't met:

* The given type `T` is not a sealed trait, sealed abstract class or sealed
  class
* The case instances of type `T` are not all `case object`s

## Contributors

* Travis Brown (library based on his [StackOverflow post](http://stackoverflow.com/a/13672520))
* Branden Visser (core maintainer)
