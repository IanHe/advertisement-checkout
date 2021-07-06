package spec
import org.scalatest.Inside
import org.scalatest.Inspectors
import org.scalatest.OptionValues
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpecLike

trait UnitSpec extends AnyWordSpecLike with Matchers with OptionValues with Inside with Inspectors

