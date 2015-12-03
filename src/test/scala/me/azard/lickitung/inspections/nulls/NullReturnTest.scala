package me.azard.lickitung.inspections.nulls

import com.sksamuel.scapegoat.PluginRunner

import org.scalatest.{ FreeSpec, Matchers, OneInstancePerTest }

/** @author Azard */
class NullReturnTest extends FreeSpec with Matchers with PluginRunner with OneInstancePerTest {

  override val inspections = Seq(new NullReturn)

  "NullReturn" - {
    "should report warning" - {
      "for function return null in literal function" in {

        val code =
          """class Bar {
            |  def foo = {
            |    null
            |  }
            |}
            |object Test { }""".stripMargin

        compileCodeSnippet(code)
        compiler.scapegoat.feedback.warnings.size shouldBe 1
      }

      "for function return null in block function" in {

        val code =
          """class Bar {
            |  def foo = {
            |    val a = 1
            |    val b = 2
            |    val c = a + b
            |    null
            |  }
            |}
            |object Test { }""".stripMargin

        compileCodeSnippet(code)
        compiler.scapegoat.feedback.warnings.size shouldBe 1
      }
    }


    "should not report warning" - {
      "for function return is not null" in {
        val code =
          """class Bar {
            |  def foo = {
            |    val a = 1
            |    val b = 2
            |    val c = a + b
            |    c
            |  }
            |}
            |object Test { }""".stripMargin

        compileCodeSnippet(code)
        compiler.scapegoat.feedback.warnings.size shouldBe 0
      }
    }
  }
}
