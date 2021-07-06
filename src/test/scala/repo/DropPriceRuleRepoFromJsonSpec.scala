package repo

import domain.AdvertisementType
import domain.DropPriceRule
import spec.UnitSpec

class DropPriceRuleRepoFromJsonSpec extends UnitSpec {
  trait Fixture {
    val filePath = "src/main/resources/db/droppricerule.db.json"
    val repo = new DropPriceRuleRepoFromJson(filePath)
    val expectedDropPriceRule: DropPriceRule = DropPriceRule(2, AdvertisementType.withName("Standout"), 299.99)
  }

  "test load json from file" in new Fixture {
    val rules = repo.load()
    rules.size shouldBe 2
    rules.head shouldBe expectedDropPriceRule
  }

  "test get DropPriceRule by customer id" should {
    "get seq of DropPriceRule by correct customer id" in new Fixture {
      val rules = repo.get(2)
      rules.nonEmpty shouldBe true
    }
    "get empty seq of DropPriceRule by unfound customer id" in new Fixture {
      val rules = repo.get(100)
      rules.isEmpty shouldBe true
    }
  }
}
