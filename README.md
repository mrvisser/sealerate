# Sealerate

[![Build Status](https://travis-ci.org/mrvisser/sealerate.svg?branch=master)](https://travis-ci.org/mrvisser/sealerate)
![Current Version](https://img.shields.io/badge/version-0.0.5-brightgreen.svg?style=flat "0.0.5")
[![Apache 2.0 License](https://img.shields.io/badge/license-Apache%202-blue.svg)](LICENSE)

Sealerate is a small (3rd-party-dependency-free) library that provides convenience
functions `values[T]` and `collect[T]` that dynamically create a set of all instances
of a sealed class.

## Set Up

Sealerate is available for Scala 2.10, 2.11, 2.12, and 2.13.0-RC1. If you are using sbt, add
the following to your `build.sbt`:

```
libraryDependencies += "ca.mrvisser" %% "sealerate" % "0.0.5"
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
* The case instances of type `T` are not all `case object`s. (As an alternative
  the `collect[T]` function will simply filter out `case class`s instead of throw
  an error)

## Contributors

* [Branden Visser](https://github.com/mrvisser) (core maintainer)
* [jibbers42](https://github.com/jibbers42) (Scala 2.12 support)
* [Philippus Baalman](https://github.com/Philippus) (Scala 2.12 support)
* [Travis Brown](https://github.com/travisbrown) (library based on his [StackOverflow post](http://stackoverflow.com/a/13672520))
