package pricingrules

import domain.AdvertisementType.AdvertisementType
import domain.Advertisement

class DropPriceApplier(
  private val priceMap: Map[AdvertisementType, BigDecimal]
) {
  def dropPrice(ad: Advertisement): Advertisement = {
    priceMap.get(ad.adsType) match {
      case Some(newPrice) => ad.copy(price = newPrice)
      case None => ad
    }
  }
}