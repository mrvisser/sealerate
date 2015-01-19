package com.pellucid.sealerate

sealed class SealedClassObjectsOnly(val name: String, val index: Int)

object SealedClassObjectsOnly {
  case object Item1 extends SealedClassObjectsOnly("item1", 0)
  case object Item2 extends SealedClassObjectsOnly("item2", 1)
}
