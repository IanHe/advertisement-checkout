package domain

import AdvertisementType.AdvertisementType

case class DropPriceRule(
  customerId: Long,
  advertisementType: AdvertisementType,
  price: BigDecimal
)
