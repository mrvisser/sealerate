package ca.mrvisser.sealerate

sealed abstract class SealedAbstractClassObjectsOnly(val name: String, val index: Int)

object SealedAbstractClassObjectsOnly {
  case object Item1 extends SealedAbstractClassObjectsOnly("item1", 0)
  case object Item2 extends SealedAbstractClassObjectsOnly("item2", 1)
}
