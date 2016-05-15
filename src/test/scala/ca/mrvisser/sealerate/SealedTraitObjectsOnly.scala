package ca.mrvisser.sealerate

sealed trait SealedTraitObjectsOnly

object SealedTraitObjectsOnly {
  case object Item1 extends SealedTraitObjectsOnly
  case object Item2 extends SealedTraitObjectsOnly
}
