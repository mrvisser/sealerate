
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

## License

This software is licensed under the Apache 2 license, quoted below.

Copyright 2015 [Pellucid Analytics](http://www.pellucid.com/)

Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at [http://www.apache.org/licenses/LICENSE-2.0](http://www.apache.org/licenses/LICENSE-2.0)

Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
