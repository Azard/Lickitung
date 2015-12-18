package me.azard.lickitung.inspections.patternmatch

import com.sksamuel.scapegoat._

class MatchErasureType extends Inspection {
  def inspector(context: InspectionContext): Inspector = new Inspector(context) {
    override def postTyperTraverser = Some apply new context.Traverser {

      import context.global._

      override def inspect(tree: Tree): Unit = {
        tree match {
          //case CaseDef(Bind(TermName(_), Typed(Ident(_), AppliedTypeTree(Ident(_), List(Ident(t))))), _, _) =>
          case CaseDef(Bind(TermName(_), Typed(_, TypeTree())), _, _) => warn(tree)
          case _ => continue(tree)
        }
      }

      private def warn(tree: Tree) {
        context.warn("Pattern match genericity",
          tree.pos,
          Levels.Warning,
          "Pattern match genericity on line " + tree.pos.line,
          MatchErasureType.this)
      }
    }
  }
}
