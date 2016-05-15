package ca.mrvisser.sealerate

package object macrocompat {
  type Context = scala.reflect.macros.Context

  def termName(c: Context)(name: String): c.universe.TermName =
    c.universe.newTermName(name)
}
