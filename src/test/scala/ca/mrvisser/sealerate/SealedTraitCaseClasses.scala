package ca.mrvisser.sealerate

sealed trait SealedTraitCaseClasses

object SealedTraitCaseClasses {
  case object TheObject extends SealedTraitCaseClasses
  case class TheClass(name: String) extends SealedTraitCaseClasses

  def objects = collect[SealedTraitCaseClasses]
}
