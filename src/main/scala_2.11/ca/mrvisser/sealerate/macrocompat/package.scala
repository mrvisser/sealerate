package ca.mrvisser.sealerate

package object macrocompat {
  type Context = scala.reflect.macros.blackbox.Context

  def termName(c: Context)(name: String): c.universe.TermName =
    c.universe.TermName(name)
}
