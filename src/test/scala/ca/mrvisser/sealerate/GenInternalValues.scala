package ca.mrvisser.sealerate

sealed trait GenInternalValues

object GenInternalValues {
  case object Item1 extends GenInternalValues
  case object Item2 extends GenInternalValues

  def itemValues = values[GenInternalValues]
  def itemCollect = collect[GenInternalValues]
}
