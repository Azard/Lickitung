package me.azard.lickitung.inspections.patternmatch

import com.sksamuel.scapegoat.PluginRunner

import org.scalatest.{ FreeSpec, Matchers, OneInstancePerTest }

/** @author Azard */
class MatchErasureTypeTest extends FreeSpec with Matchers with PluginRunner with OneInstancePerTest {

  override val inspections = Seq(new MatchErasureType)

  "MatchErasureType" - {
    "should report warning" - {
      "for match erasure type" in {

        val code =
          """
            |object Test {
            |  class Foo[+T]
            |
            |  def Bar(foo: Foo[Any]) = foo match {
            |    case foo: Foo[Boolean] => 1
            |    case foo: Foo[Int] => 2
            |    case foo: Foo[Double] => 3
            |  }
            |}
            |""".stripMargin

        compileCodeSnippet(code)
        compiler.scapegoat.feedback.warnings.size shouldBe 3
      }
    }
  }
}
