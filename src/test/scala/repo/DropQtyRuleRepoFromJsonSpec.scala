package repo

import domain.AdvertisementType
import domain.DropQtyRule
import spec.UnitSpec

class DropQtyRuleRepoFromJsonSpec extends UnitSpec {
  trait Fixture {
    val filePath = "src/main/resources/db/dropqtyrule.db.json"
    val repo = new DropQtyRuleRepoFromJson(filePath)
    val expectedDropQtyRule = DropQtyRule(1, AdvertisementType.withName("Classic"), 3, 2)
  }

  "test load json from file" in new Fixture {
    val rules = repo.load()
    rules.size shouldBe 2
    rules.head shouldBe expectedDropQtyRule
  }
  "test get DropQtyRules by customer id" should {
    "get nonEmpty seq of DropQtyRule by found customer id" in new Fixture {
      val rules = repo.get(1)
      rules.nonEmpty shouldBe true
    }
    "get empty seq of DropQtyRule by unfound customer id" in new Fixture {
      val rules = repo.get(100)
      rules.isEmpty shouldBe true
    }
  }
}
