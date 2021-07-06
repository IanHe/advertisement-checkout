package pricingrules

import domain.Advertisement
import domain.AdvertisementType
import domain.AdvertisementType.AdvertisementType
import spec.UnitSpec

class DropPriceApplierTest extends UnitSpec {
  trait Fixture {
    val priceMap: Map[AdvertisementType, BigDecimal] = Map(
      AdvertisementType.Classic -> 100,
      AdvertisementType.Premium -> 50
    )
    val dropPriceApplier = new DropPriceApplier(priceMap)
  }

  "test dropPrice()" should {
    "return a copy of drop-priced advertisement given the advertisement has drop price rule" in new Fixture {
      val ad = Advertisement(AdvertisementType.Classic, 200, 1, "someDescription")
      val newAd = dropPriceApplier.dropPrice(ad)
      newAd should not be ad
      newAd.price shouldBe 100
    }
    "return the same advertisement given the advertisement has no drop price rule" in new Fixture {
      val ad = Advertisement(AdvertisementType.Standout, 200, 1, "someDescription")
      val newAd = dropPriceApplier.dropPrice(ad)
      newAd shouldBe ad
      newAd.price shouldBe 200
    }
  }
}
