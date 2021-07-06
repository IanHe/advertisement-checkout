package domain

import domain.AdvertisementType.AdvertisementType

case class Advertisement(
  adsType: AdvertisementType,
  price: BigDecimal,
  qty: Long,
  description: String
) {
  def addQtyByOne(): Advertisement = this.copy(qty = qty + 1)

  def totalPrice(): BigDecimal = price * qty
}
