package react.common

import cats.implicits._
import react.common.implicits._
import utest._

object StyleTests extends TestSuite with TestUtils {
  val tests = Tests {
    test("style") {
      val a: Style = Style(Map("height" -> 42, "background" -> "black"))
      val b: Style = Style(Map("height" -> 42, "background" -> "black"))
      val c: Style = Style(Map("height" -> 42, "background" -> "black"))
      assert((a |+| b) === c)
    }
    test("extract") {
      val a: Style = Style(Map("height" -> 42, "background" -> "black"))
      assert(a.extract[Int]("height") == 42.some)
      assert(a.extract[String]("height").isEmpty)
      assert(a.extract[String]("background") == "black".some)
    }
    test("remove") {
      val a: Style = Style(Map("height" -> 42, "background" -> "black"))
      assert(a.remove("height").extract[String]("height").isEmpty)
    }
    test("when") {
      assert(Style(Map("height" -> 42, "background" -> "black")).when(true).nonEmpty)
      assert(Style(Map("height" -> 42, "background" -> "black")).when(false).isEmpty)
    }
    test("unless") {
      assert(Style(Map("height" -> 42, "background" -> "black")).unless(true).isEmpty)
      assert(Style(Map("height" -> 42, "background" -> "black")).unless(false).nonEmpty)
    }
  }
}
