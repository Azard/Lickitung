package me.azard.lickitung.inspections.unnecessary

import com.sksamuel.scapegoat.PluginRunner
import org.scalatest.{ FreeSpec, Matchers, OneInstancePerTest }

/** @author Stephen Samuel
  * @author Azard           */
class UnnecessaryOverrideTest
  extends FreeSpec
  with Matchers
  with PluginRunner
  with OneInstancePerTest {

  override val inspections = Seq(new UnnecessaryOverride)

  "UnnecessaryOverride" - {
    "should report warning" - {
      "when overriding method to just call super" in {

        val code =
          """class Test {
            |  override def finalize() {
            |    super.finalize()
            |  }
            |}
          """.stripMargin

        compileCodeSnippet(code)
        compiler.scapegoat.feedback.warnings.size shouldBe 1
      }
    }
    "should not report warning" - {
      "when overriding method with super call and other code" in {

        val code =
          """class Test {
            |  override def finalize() {
            |    super.finalize()
            |    println("sam")
            |  }
            |}
          """.stripMargin

        compileCodeSnippet(code)
        compiler.scapegoat.feedback.warnings.size shouldBe 0
      }
    }
    "should not report warning" - {
      "when overriding method with super call and new parameters" in {

        val code =
          """abstract class Printer {
            |  def print(message: String): Unit = println(message)
            |}
            |class FriendlyPrinter extends Printer {
            |  override def print(message: String): Unit =
            |    super.print(s"Hello, $message!")
            |}
          """.stripMargin

        compileCodeSnippet(code)
        compiler.scapegoat.feedback.warnings.size shouldBe 0
      }
    }
  }
}