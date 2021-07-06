package repo

import domain.Advertisement
import mixin.FileReader
import mixin.JsonSupport

class AdvertisementRepoFromJson(filePath: String) extends AdvertisementRepo with JsonSupport with FileReader {
  def load(): Seq[Advertisement] = {
    val str = readFile(filePath)
    val json = parseJsonStr(str)
    json.as[Seq[Advertisement]].getOrElse(throw new Exception(s"read json file: ${filePath} failed"))
  }
}
