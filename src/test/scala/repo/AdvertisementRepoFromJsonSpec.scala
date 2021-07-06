package repo

import domain.Advertisement
import domain.AdvertisementType
import spec.UnitSpec

class AdvertisementRepoFromJsonSpec extends UnitSpec {
  "test load json from file" in {
    val filePath = "src/main/resources/db/advertisement.db.json"
    val ads = new AdvertisementRepoFromJson(filePath).load()
    val expectedAd = Advertisement(AdvertisementType.withName("Classic"), 269.99, 0, "Offers the most basic level of advertisement")
    ads.size shouldBe 3
    ads.head shouldBe expectedAd
  }
}
