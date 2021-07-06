package repo

import domain.Customer
import mixin.FileReader
import mixin.JsonSupport

class CustomerRepoFromJson(filePath: String) extends CustomerRepo with FileReader with JsonSupport {
  val customers: Seq[Customer] = load()

  def get(id: Long): Option[Customer] = {
    customers.find(c => c.id == id)
  }

  private[repo] def load(): Seq[Customer] = {
    val str = readFile(filePath)
    val json = parseJsonStr(str)
    json.as[Seq[Customer]].getOrElse(throw new Exception(s"read json file: ${filePath} failed"))
  }
}
