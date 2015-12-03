package me.azard.lickitung.inspections.style

import com.sksamuel.scapegoat.PluginRunner

import org.scalatest.{ FreeSpec, Matchers, OneInstancePerTest }

/** @author Azard */
class ImportOrderTest extends FreeSpec with Matchers with PluginRunner with OneInstancePerTest {

  override val inspections = Seq(new ImportOrder)

  "ImportOrder" - {
    "should report warning" - {
      "for bad import order style1" in {

        val code =
          """import scala.concurrent.duration.TimeUnit
             import java.io.Bits
             object Test { }""".stripMargin

        compileCodeSnippet(code)
        compiler.scapegoat.feedback.warnings.size shouldBe 1
      }

      "for bad import order style2" in {

        val code =
          """import scala.concurrent.duration.TimeUnit
             import javax.net.DefaultServerSocketFactory
             object Test { }""".stripMargin

        compileCodeSnippet(code)
        compiler.scapegoat.feedback.warnings.size shouldBe 1
      }
    }

    "should not report warning" - {
      "for good import order style" in {
        val code =
          """import java.io.Bits
             import javax.net.DefaultServerSocketFactory
             import scala.concurrent.duration.TimeUnit
             import com.oracle.net.Sdp
             object Test { }""".stripMargin

        compileCodeSnippet(code)
        compiler.scapegoat.feedback.warnings.size shouldBe 0
      }
    }
  }
}
