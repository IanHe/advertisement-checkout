package pricingrules

import domain.Advertisement
import domain.AdvertisementType
import domain.AdvertisementType.AdvertisementType
import domain.DropQtyRule
import spec.UnitSpec

class PricingRulesSpec extends UnitSpec {
  trait Fixture {
    val dropQtyRule = DropQtyRule(1, AdvertisementType.Classic, 5, 3)
    val dropQtyRuleMap: Map[AdvertisementType, DropQtyRule] = Map(
      AdvertisementType.Classic -> dropQtyRule
    )
    val dropQtyRuleFinder = new DropQtyRuleFinder(dropQtyRuleMap)
    val priceMap: Map[AdvertisementType, BigDecimal] = Map(
      AdvertisementType.Classic -> 100,
      AdvertisementType.Premium -> 50
    )
    val dropPriceApplier = new DropPriceApplier(priceMap)
    val pricingRules = new PricingRules(dropQtyRuleFinder, dropPriceApplier)
  }

  "test applying rules" should {
    "apply both DropQtyRule and DropPriceRule for one and do not apply DropQtyRule for another one" in new Fixture {
      val ad1 = Advertisement(AdvertisementType.Classic, 200, 5, "someDescription")
      val ad2 = Advertisement(AdvertisementType.Premium, 200, 5, "someDescription")
      val newAds = pricingRules.apply(Seq(ad1, ad2))
      newAds.head should not be ad1
      newAds.last should not be ad2
      newAds.head.qty shouldBe 3
      newAds.head.price shouldBe 100
      newAds.last.qty shouldBe 5
      newAds.last.price shouldBe 50
    }
    "not apply rules on the advertisement" in new Fixture {
      val ad1 = Advertisement(AdvertisementType.Standout, 200, 5, "someDescription")
      val ad2 = Advertisement(AdvertisementType.Standout, 200, 5, "someDescription")
      val newAds = pricingRules.apply(Seq(ad1, ad2))
      newAds should contain theSameElementsAs Seq(ad1, ad2)
    }
  }

  "test dropQty" should {
    "drop quantity and return a new Advertisement given DropQtyRule exists" in new Fixture {
      val ad = Advertisement(AdvertisementType.Classic, 200, 5, "someDescription")
      val newAd = pricingRules.dropQty(ad)
      newAd should not be ad
      newAd.qty shouldBe 3
    }
    "not drop quantity and return the same Advertisement given DropQtyRule not exists" in new Fixture {
      val ad = Advertisement(AdvertisementType.Premium, 200, 5, "someDescription")
      val newAd = pricingRules.dropQty(ad)
      newAd shouldBe ad
    }
  }
}
