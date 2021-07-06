package mixin

import domain.Advertisement
import domain.AdvertisementType
import domain.AdvertisementType.AdvertisementType
import domain.CheckoutRequest
import domain.Customer
import domain.DropPriceRule
import domain.DropQtyRule
import io.circe.Decoder
import io.circe.Json
import io.circe.generic.extras.AutoDerivation
import io.circe.generic.semiauto.deriveDecoder
import io.circe.parser.parse

trait JsonSupport extends AutoDerivation {

  protected def parseJsonStr(str: String): Json = parse(str) match {
    case Left(parsingFailure) => throw parsingFailure
    case Right(json) => json
  }

  implicit val customerDecoder: Decoder[Customer] = deriveDecoder[Customer]
  implicit val advertisementTypeDecoder: Decoder[AdvertisementType] = Decoder.decodeEnumeration(AdvertisementType)
  implicit val advertisementDecoder: Decoder[Advertisement] = deriveDecoder[Advertisement]
  implicit val dropPriceRuleDecoder: Decoder[DropPriceRule] = deriveDecoder[DropPriceRule]
  implicit val dropQtyRuleDecoder: Decoder[DropQtyRule] = deriveDecoder[DropQtyRule]
  implicit val requestDecoder: Decoder[CheckoutRequest] = deriveDecoder[CheckoutRequest]
}
