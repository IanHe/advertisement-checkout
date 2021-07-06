package repo

import domain.Advertisement
import mixin.FileReader
import mixin.JsonSupport

trait AdvertisementRepo {
  def load(): Seq[Advertisement]
}


