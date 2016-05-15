package ca.mrvisser.sealerate.internal

import ca.mrvisser.sealerate.macrocompat

object Macros {

  /**
    * Permutation of the object instance collector that throws an error if there
    * are any `case class` instances.
    *
    * @see [[internalValuesImpl]]
    */
  def valuesImpl[T: c.WeakTypeTag](c: macrocompat.Context): c.Expr[Set[T]] =
    internalValuesImpl(filterCaseClass = false)(c)

  /**
    * Permutation of the object instance collector that silently filters out any
    * `case class` instances.
    *
    * @see [[internalValuesImpl]]
    */
  def collectImpl[T: c.WeakTypeTag](c: macrocompat.Context): c.Expr[Set[T]] =
    internalValuesImpl(filterCaseClass = true)(c)

  /**
    * The actual macro that handles extracting object instances of a sealed
    * class.
    *
    * @tparam T  The type (sealed trait, abstract class or class) for which we
    *            want to search object instances
    * @return    An instance of a set that contains all known object instances
    *            for the type [[T]]
    */
  private[this] def internalValuesImpl[T: c.WeakTypeTag](filterCaseClass: Boolean)(c: macrocompat.Context): c.Expr[Set[T]] = {
    import c.universe._

    val enumTpe = weakTypeOf[T].typeSymbol

    // The given type T must be a class and it must be sealed
    if (!enumTpe.isClass || !enumTpe.asClass.isSealed) {
      c.abort(c.enclosingPosition, "Can only enumerate values of a sealed trait or class.")
    }

    val enumClass = enumTpe.asClass
    val enumClassChildren = enumClass.knownDirectSubclasses.toList

    /**
      * There are some potential issues with `knownDirectSubclasses`. Let's try
      * and alleviate some pain by warning when it turns out to be empty.
      */
    if (enumClassChildren.isEmpty) {
      c.warning(
        c.enclosingPosition,
        s"""
           | Enumeration generation for type $enumClass failed to find any
           | instances. There are bugs in scala macros that may lead to this:
           |
           |  * If you are generating instances of a sealed class that extends
           |    another class, this does not work; or
           |
           |  * If you assign the generated instances to a `val` instead of a
           |    `def`, then the generated instances will be empty
           |
      """.stripMargin
      )
    }


    val enumModuleChildren = enumClassChildren.filter(_.isModuleClass)

    // All known direct subclasses must be `case object`s, because we cannot
    // generate instances of case classes that take parameters. Depending on
    // the value of `filterCaseClass`, we will either filter out the class
    // permutations or throw a compilation error
    if (!filterCaseClass && enumClassChildren.size != enumModuleChildren.size) {
      c.abort(c.enclosingPosition, "All children must be objects.")
    }

    // Generate an expression that passes all case object instances into a Set
    c.Expr[Set[T]](
      Apply(
        Select(
          reify(Set).tree,
          macrocompat.termName(c)("apply")
        ),
        enumModuleChildren.map(child => Ident(child.asClass.module))
      )
    )
  }
}
