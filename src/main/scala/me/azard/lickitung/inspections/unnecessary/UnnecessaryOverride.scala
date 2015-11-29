package me.azard.lickitung.inspections.unnecessary

import com.sksamuel.scapegoat.{Inspection, InspectionContext, Inspector, Levels}

/** @author Azard */
class UnnecessaryOverride extends Inspection {

  def inspector(context: InspectionContext): Inspector = new Inspector(context) {
    override def postTyperTraverser = Some apply new context.Traverser {

      import context.global._

      override def inspect(tree: Tree): Unit = {
        tree match {
          case DefDef(mods, name, _, vparamss, _, Apply(Select(Super(This(_), _), name2), args))
            if name == name2 && vparamss.foldLeft(0)((a, b) => a + b.size) == args.size =>
            args match {
              case List(Apply(Select(Apply(_, _), TermName("s")), List(_))) => continue(tree)
              case _ =>
                context.warn("Unnecessary Override",
                  tree.pos,
                  Levels.Info,
                  "This method is overriden yet only calls super: " + tree.toString().take(200),
                  UnnecessaryOverride.this)
            }
          case _ => continue(tree)
        }
      }
    }
  }
}
