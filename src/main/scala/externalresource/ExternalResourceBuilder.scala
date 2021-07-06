package externalresource

import com.typesafe.config.Config

class ExternalResourceBuilder(config: Config) {
  def build(): ExternalResource = ExternalResource(
    customerDBFilePath = config.getString("customerDBFilePath"),
      advertisementDBFilePath = config.getString("advertisementDBFilePath"),
      dropPriceRuleDBFilePath = config.getString("dropPriceRuleDBFilePath"),
      dropQtyRuleDBFilePath = config.getString("dropQtyRuleDBFilePath"),
  )
}
