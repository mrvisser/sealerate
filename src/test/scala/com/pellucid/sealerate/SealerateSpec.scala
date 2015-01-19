package com.pellucid.sealerate

import org.scalatest.Matchers
import org.scalatest.path

class SealerateSpec extends path.FunSpec with Matchers {

  describe("values") {
    it("should give a list of values for sealed classes") {
      values[SealedTraitObjectsOnly] should be(Set(SealedTraitObjectsOnly.Item1, SealedTraitObjectsOnly.Item2))
      values[SealedAbstractClassObjectsOnly] should be(Set(SealedAbstractClassObjectsOnly.Item1, SealedAbstractClassObjectsOnly.Item2))
      values[SealedClassObjectsOnly] should be(Set(SealedClassObjectsOnly.Item1, SealedClassObjectsOnly.Item2))
    }

    it("should allow a companion object to bind to it") {
      GenInternalValues.items should be(Set(GenInternalValues.Item1, GenInternalValues.Item2))
    }

    it("should give an empty set for a sealed trait with no instances") {
      values[SealedTraitEmpty] should be(Set.empty[SealedTraitEmpty])
    }
  }
}
