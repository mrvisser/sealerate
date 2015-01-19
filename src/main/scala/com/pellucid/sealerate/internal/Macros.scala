package com.pellucid.sealerate.internal

import com.pellucid.sealerate.macrocompat

object Macros {

  /**
   * The actual macro that handles extracting object instances of a sealed
   * class.
   *
   * @tparam T  The type (sealed trait, abstract class or class) for which we
   *            want to search object instances
   * @return    An instance of a set that contains all known object instances
   *            for the type [[T]]
   */
  def valuesImpl[T: c.WeakTypeTag](c: macrocompat.Context): c.Expr[Set[T]] = {
    import c.universe._

    val enumTpe = weakTypeOf[T].typeSymbol

    // The given type T must be a class and it must be sealed
    if (!enumTpe.isClass || !enumTpe.asClass.isSealed) {
      c.abort(c.enclosingPosition, "Can only enumerate values of a sealed trait or class.")
    }

    val enumClass = enumTpe.asClass
    val enumClassChildren = enumClass.knownDirectSubclasses.toList

    // All known direct subclasses must be `case object`s, because we cannot
    // generate instances of case classes that take parameters
    if (!enumClassChildren.forall(_.isModuleClass)) {
      c.abort(c.enclosingPosition, "All childrens must be objects.")
    }

    // Generate an expression that passes all case object instances into a Set
    c.Expr[Set[T]](
      Apply(
        Select(
          reify(Set).tree,
          macrocompat.termName(c)("apply")
        ),
        enumClassChildren.map(child => Ident(child.asClass.module))
      )
    )
  }
}
