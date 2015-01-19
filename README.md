
## Sealerate

Sealerate is a simple library that provides a convenience function `values[T]` that dynamically creates a set of all instances of a sealed class.

## Example Usage

```scala
import example

import com.pellucid.sealerate

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

Now `Switch.values` will provide `Set(On, Off)`, and wil update automatically when new objects are added.

Note that a compilation error is thrown if the following conditions aren't met:

* The given type `T` is not a sealed trait, sealed abstract class or sealed class
* The case instances of type `T` are not all `case object`s
