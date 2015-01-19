package com.pellucid.sealerate

sealed trait GenInternalValues

object GenInternalValues {
  case object Item1 extends GenInternalValues
  case object Item2 extends GenInternalValues

  def items = values[GenInternalValues]
}