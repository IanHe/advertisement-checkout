package service

import domain.Advertisement
import domain.AdvertisementType
import domain.AdvertisementType.AdvertisementType
import domain.DropQtyRule
import pricingrules.DropPriceApplier
import pricingrules.DropQtyRuleFinder
import pricingrules.PricingRules
import spec.UnitSpec

class CheckoutSpec extends UnitSpec {
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
    val checkout = new Checkout(pricingRules)

    val ad1 = Advertisement(AdvertisementType.Classic, 200, 5, "someDescription")
    val ad2 = Advertisement(AdvertisementType.Premium, 200, 5, "someDescription")
    val ad3 = Advertisement(AdvertisementType.Standout, 200, 5, "someDescription")
  }

  "test add items should increase qty for some Advertisement and keep the same for the other Advertisement" in new Fixture {
    checkout.add(Seq(ad1, ad2, ad3, ad1, ad2))
    checkout.adsMap.size shouldBe 3

    val resultAd1 = checkout.adsMap(AdvertisementType.Classic)
    val resultAd2 = checkout.adsMap(AdvertisementType.Premium)
    val resultAd3 = checkout.adsMap(AdvertisementType.Standout)
    resultAd1 should not be ad1
    resultAd2 should not be ad2
    resultAd3 shouldBe ad3
    resultAd1.qty shouldBe 6
    resultAd2.qty shouldBe 6
  }

  "test getTotal price" should {
    "get expected price when drop qty rule and drop price rule are applied" in new Fixture {
      checkout.add(Seq(ad1, ad1))
      checkout.total() shouldBe 400
    }
    "get expected price when only drop price rule are applied" in new Fixture {
      checkout.add(Seq(ad2, ad2))
      checkout.total() shouldBe 300
    }
    "get expected price when non rules are applied" in new Fixture {
      checkout.add(Seq(ad3, ad3))
      checkout.total() shouldBe 1200
    }
    "get expected total price when various kinds of items are mixed" in new Fixture {
      checkout.add(Seq(ad1, ad1, ad2, ad2, ad3, ad3))
      checkout.total() shouldBe (400 + 300 + 1200)
    }
  }
}
