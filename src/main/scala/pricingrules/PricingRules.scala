package pricingrules

import domain.Advertisement

class PricingRules(
  val dropQtyRuleFinder: DropQtyRuleFinder,
  val dropPriceApplier: DropPriceApplier
) {
  def apply(ads: Seq[Advertisement]): Seq[Advertisement] = {
    ads.map { ad =>
      val qtyDroppedAd = dropQty(ad)
      val priceDroppedAd = dropPriceApplier.dropPrice(qtyDroppedAd)
      priceDroppedAd
    }
  }

  private[pricingrules] def dropQty(ad: Advertisement): Advertisement = {
    dropQtyRuleFinder.find(ad.adsType) match {
      case Some(rule) =>
        val newQty = rule.dropQty(ad.adsType, ad.qty)
        ad.copy(qty = newQty)
      case None => ad
    }
  }
}
