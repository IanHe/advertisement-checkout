package service

import domain.Advertisement
import domain.AdvertisementType.AdvertisementType
import pricingrules.PricingRules

class Checkout(val pricingRules: PricingRules) {
  var adsMap: Map[AdvertisementType, Advertisement] = Map.empty

  def add(items: Seq[Advertisement]): Unit = {
    items.foreach(addOne)
  }

  def total(): BigDecimal = {
    val ads = adsMap.values.toSeq
    val newAds = pricingRules.apply(ads)
    newAds.foldLeft(BigDecimal(0))((sum, ad) => sum + ad.totalPrice())
  }

  private[service] def addOne(item: Advertisement): Unit = {
    adsMap.get(item.adsType) match {
      case Some(ad) => adsMap = adsMap + (item.adsType -> ad.addQtyByOne())
      case None => adsMap = adsMap + (item.adsType -> item)
    }
  }
}

object Checkout {
  def apply(pricingRules: PricingRules): Checkout = new Checkout(pricingRules)
}
