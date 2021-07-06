package externalresource

case class ExternalResource(
  customerDBFilePath: String,
  advertisementDBFilePath: String,
  dropPriceRuleDBFilePath: String,
  dropQtyRuleDBFilePath: String
)
