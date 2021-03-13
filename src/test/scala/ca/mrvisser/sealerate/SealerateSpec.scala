package ca.mrvisser.sealerate

import org.scalatest.funspec.PathAnyFunSpec
import org.scalatest.matchers.should.Matchers

class SealerateSpec extends PathAnyFunSpec with Matchers {

  describe("values") {
    it("should give a list of values for sealed classes") {
      values[SealedTraitObjectsOnly] should be(Set(SealedTraitObjectsOnly.Item1, SealedTraitObjectsOnly.Item2))
      values[SealedAbstractClassObjectsOnly] should be(Set(SealedAbstractClassObjectsOnly.Item1, SealedAbstractClassObjectsOnly.Item2))
      values[SealedClassObjectsOnly] should be(Set(SealedClassObjectsOnly.Item1, SealedClassObjectsOnly.Item2))
    }

    it("should allow a companion object to bind to it") {
      GenInternalValues.itemValues should be(Set(GenInternalValues.Item1, GenInternalValues.Item2))
    }

    it("should give an empty set for a sealed trait with no instances") {
      values[SealedTraitEmpty] should be(Set.empty[SealedTraitEmpty])
    }
  }

  describe("collect") {
    it("should give a list of values for sealed classes") {
      collect[SealedTraitObjectsOnly] should be(Set(SealedTraitObjectsOnly.Item1, SealedTraitObjectsOnly.Item2))
      collect[SealedAbstractClassObjectsOnly] should be(Set(SealedAbstractClassObjectsOnly.Item1, SealedAbstractClassObjectsOnly.Item2))
      collect[SealedClassObjectsOnly] should be(Set(SealedClassObjectsOnly.Item1, SealedClassObjectsOnly.Item2))
    }

    it("should simply filter out case class instances") {
      SealedTraitCaseClasses.objects should be(Set(SealedTraitCaseClasses.TheObject))
    }

    it("should allow a companion object to bind to it") {
      GenInternalValues.itemCollect should be(Set(GenInternalValues.Item1, GenInternalValues.Item2))
    }

    it("should give an empty set for a sealed trait with no instances") {
      collect[SealedTraitEmpty] should be(Set.empty[SealedTraitEmpty])
    }
  }
}
