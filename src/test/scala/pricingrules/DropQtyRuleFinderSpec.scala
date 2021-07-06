package pricingrules

import domain.AdvertisementType
import domain.AdvertisementType.AdvertisementType
import domain.DropQtyRule
import spec.UnitSpec

class DropQtyRuleFinderSpec extends UnitSpec {
  trait Fixture {
    val expectedRule = DropQtyRule(1, AdvertisementType.Classic, 5, 3)
    val dropQtyRuleMap: Map[AdvertisementType, DropQtyRule] = Map(
      AdvertisementType.Classic -> expectedRule
    )
    val finder = new DropQtyRuleFinder(dropQtyRuleMap)
  }

  "test find DropQtyRule" should {
    "get DropQtyRule given AdvertisementType is found in dropQtyRuleMap" in new Fixture {
      val rule = finder.find(AdvertisementType.Classic)
      rule shouldBe Some(expectedRule)
    }
    "get None given AdvertisementType is not found in dropQtyRuleMap" in new Fixture {
      val rule = finder.find(AdvertisementType.Premium)
      rule shouldBe None
    }
  }
}
