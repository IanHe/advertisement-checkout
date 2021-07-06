package domain

import domain.AdvertisementType.AdvertisementType

case class DropQtyRule(
  customerId: Long,
  advertisementType: AdvertisementType,
  baseNum: Long,
  outputNum: Long
) {
  def dropQty(_adsType: AdvertisementType, qty: Long): Long = {
    if (_adsType == advertisementType) dropNum(qty) else qty
  }

  private def dropNum(num: Long): Long = {
    (num / baseNum) * outputNum + (num % baseNum)
  }
}
