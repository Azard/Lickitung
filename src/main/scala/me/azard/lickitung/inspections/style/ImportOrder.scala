package me.azard.lickitung.inspections.style

import com.sksamuel.scapegoat.{ Levels, Inspection, InspectionContext, Inspector }

/** @author Azard */
class ImportOrder extends Inspection {

  override def inspector(context: InspectionContext): Inspector = new Inspector(context) {

    private val javaImport = 0
    private val javaxImport = 1
    private val scalaImport = 2
    private val otherImport = 3
    private var importOrder = javaImport
    private var warn = false

    override def postTyperTraverser = Some apply new context.Traverser {

      import context.global._

      override def inspect(tree: Tree): Unit = {
        tree match {
          case PackageDef(_, _) =>
            importOrder = javaImport; continue(tree)
          case ModuleDef(_, _, _) =>
            importOrder = javaImport; continue(tree)
          case ClassDef(_, _, _, _) =>
            importOrder = javaImport; continue(tree)
          case Import(expr, selectors) =>
            var firstName: String = ""
            var firstSection = expr
            while (firstName == "") {
              firstSection match {
                case Select(child, _) => firstSection = child
                case Ident(child)     => firstName = child.toString
              }
            }
            firstName match {
              case "java" =>
                if (importOrder > javaImport) warn = true
              case "javax" =>
                if (importOrder > javaxImport) warn = true
                importOrder = javaxImport
              case "scala" =>
                if (importOrder > scalaImport) warn = true
                importOrder = scalaImport
              case _ =>
                importOrder = otherImport
            }
            if (warn) {
              context.warn("Bad Import Order Style", tree.pos, Levels.Info, expr.toString + "." + selectors.toString, ImportOrder.this)
            }
          case DefDef(_, _, _, _, _, _) => // check imports inside defs
          case _                        => continue(tree)
        }
      }
    }
  }

}
