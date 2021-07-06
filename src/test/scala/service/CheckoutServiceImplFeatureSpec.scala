package service

import com.typesafe.config.ConfigFactory
import domain.AdvertisementType
import domain.CheckoutRequest
import externalresource.ExternalResourceBuilder
import spec.UnitSpec

class CheckoutServiceImplFeatureSpec extends UnitSpec {
  trait Fixture {
    val config = ConfigFactory.parseResources("application.conf")
    val resource = new ExternalResourceBuilder(config).build()
    val serviceAssembler = new ServiceAssembler(resource)
    val service = serviceAssembler.getCheckoutService()
  }

  "test handleRequest" in new Fixture {
    val request1 = CheckoutRequest(0, Seq(AdvertisementType.Classic, AdvertisementType.Standout, AdvertisementType.Premium))
    val request2 = CheckoutRequest(2, Seq(AdvertisementType.Standout, AdvertisementType.Standout, AdvertisementType.Standout, AdvertisementType.Premium))
    val request3 = CheckoutRequest(1, Seq(AdvertisementType.Classic, AdvertisementType.Classic, AdvertisementType.Classic, AdvertisementType.Premium))
    service.handleRequest(request1) shouldBe 987.97
    service.handleRequest(request2) shouldBe 1294.96
    service.handleRequest(request3) shouldBe 934.97
  }
}
