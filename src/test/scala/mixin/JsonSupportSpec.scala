package mixin

import domain.AdvertisementType
import domain.CheckoutRequest
import spec.UnitSpec

class JsonSupportSpec extends UnitSpec with JsonSupport with FileReader {
  "decode CheckoutRequest" in {
    val filePath = "src/main/resources/inputexamples/scenario-1.json"
    val str = readFile(filePath)
    val request = parseJsonStr(str).as[CheckoutRequest].getOrElse(throw new Exception)
    request.customerId shouldBe 0
    request.items.head shouldBe AdvertisementType.withName("Classic")
  }
}
