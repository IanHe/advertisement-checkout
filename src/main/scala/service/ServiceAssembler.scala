package service

import externalresource.ExternalResource
import repo.AdvertisementRepoFromJson
import repo.CustomerRepoFromJson
import repo.DropPriceRuleRepoFromJson
import repo.DropQtyRuleRepoFromJson

class ServiceAssembler(resource: ExternalResource) {
  def getCustomerRepo() = new CustomerRepoFromJson(resource.customerDBFilePath)

  def getAdvertisementRepo() = new AdvertisementRepoFromJson(resource.advertisementDBFilePath)

  def getDropPriceRuleRepo() = new DropPriceRuleRepoFromJson(resource.dropPriceRuleDBFilePath)

  def getDropQtyRuleRepo() = new DropQtyRuleRepoFromJson(resource.dropQtyRuleDBFilePath)

  def getCheckoutService() = new CheckoutServiceImpl(
    customerRepo = getCustomerRepo(),
    advertisementRepo = getAdvertisementRepo(),
    dropQtyRuleRepo = getDropQtyRuleRepo(),
    dropPriceRuleRepo = getDropPriceRuleRepo()
  )
}
