package service

import domain.Advertisement
import domain.AdvertisementType.AdvertisementType
import domain.CheckoutRequest
import domain.DropPriceRule
import domain.DropQtyRule
import pricingrules.DropPriceApplier
import pricingrules.DropQtyRuleFinder
import pricingrules.PricingRules
import repo.AdvertisementRepo
import repo.CustomerRepo
import repo.DropPriceRuleRepo
import repo.DropQtyRuleRepo

class CheckoutServiceImpl(
  private val customerRepo: CustomerRepo,
  private val advertisementRepo: AdvertisementRepo,
  private val dropQtyRuleRepo: DropQtyRuleRepo,
  private val dropPriceRuleRepo: DropPriceRuleRepo
) extends CheckoutService {
  val ads: Map[AdvertisementType, Advertisement] = advertisementRepo.load().map(ad => ad.adsType -> ad).toMap

  def handleRequest(request: CheckoutRequest): BigDecimal = {
    val checkout = getCheckOut(request.customerId).getOrElse(throw new Exception(s"unrecognized customer ${request.customerId}"))
    val itemAds = request.items.flatMap(item => ads.get(item)).map(_.copy(qty = 1))
    checkout.add(itemAds)
    checkout.total()
  }

  private def getCheckOut(customerId: Long): Option[Checkout] = {
    val someCustomer = customerRepo.get(customerId)
    val somePricingRules = someCustomer.map { customer =>
      val dropQtyRuleFinder = getDropQtyRuleFinder(customer.id)
      val dropPriceApplier = getDropPriceApplier(customer.id)
      new PricingRules(dropQtyRuleFinder, dropPriceApplier)
    }
    somePricingRules.map(new Checkout(_))
  }

  private def getDropQtyRuleFinder(customerId: Long): DropQtyRuleFinder = {
    val dropQtyRules: Seq[DropQtyRule] = dropQtyRuleRepo.get(customerId)
    val dropQtyRuleMap = dropQtyRules.map(rule => rule.advertisementType -> rule).toMap
    new DropQtyRuleFinder(dropQtyRuleMap)
  }

  private def getDropPriceApplier(customerId: Long): DropPriceApplier = {
    val dropPriceRules: Seq[DropPriceRule] = dropPriceRuleRepo.get(customerId)
    val map = dropPriceRules.map(rule => rule.advertisementType -> rule.price).toMap
    new DropPriceApplier(map)
  }
}
