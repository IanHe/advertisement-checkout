package spec

import org.scalatest.concurrent.ScalaFutures
import org.scalatest.time.Day
import org.scalatest.time.Millis
import org.scalatest.time.Span
import scala.concurrent.duration._ //scalastyle:ignore

trait AsyncUnitSpec extends UnitSpec with ScalaFutures {
  implicit val defaultPatienceConfig = PatienceConfig(
    timeout = scaled(Span(1, Day)),
    interval = scaled(Span(15, Millis)))

  implicit val defaultDuration = 30.minutes
}
