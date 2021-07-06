package pricingrules

import domain.AdvertisementType.AdvertisementType
import domain.DropQtyRule

class DropQtyRuleFinder(
  private val dropQtyRuleMap: Map[AdvertisementType, DropQtyRule]
) {
  def find(adsType: AdvertisementType): Option[DropQtyRule] =
    dropQtyRuleMap.get(adsType)
}
