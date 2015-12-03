package me.azard.lickitung.inspections.nulls

import com.sksamuel.scapegoat._

/** @author Azard */
class NullReturn extends Inspection {

  def inspector(context: InspectionContext): Inspector = new Inspector(context) {
    override def postTyperTraverser = Some apply new context.Traverser {

      import context.global._

      def containsNull(trees: List[Tree]) = trees exists {
        case Literal(Constant(null)) => true
        case _                       => false
      }

      override def inspect(tree: Tree): Unit = {
        tree match {
          case DefDef(_, _, _, _, _, ret) =>
            ret match {
              case Block(_, Literal(Constant(c))) =>
                if (c == null) warn(tree)
              case Literal(Constant(c)) =>
                if (c == null) warn(tree)
              case _ => continue(tree)
            }
          case _ => continue(tree)
        }
      }

      private def warn(tree: Tree) {
        context.warn("Null return",
          tree.pos,
          Levels.Warning,
          "Null return on line " + tree.pos.line,
          NullReturn.this)
      }
    }
  }
}