package domain

import domain.AdvertisementType.AdvertisementType

case class CheckoutRequest(
  customerId: Long,
  items: Seq[AdvertisementType]
)
