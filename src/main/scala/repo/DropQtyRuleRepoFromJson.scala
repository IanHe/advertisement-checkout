package repo

import domain.DropQtyRule
import mixin.FileReader
import mixin.JsonSupport

class DropQtyRuleRepoFromJson(filePath: String) extends DropQtyRuleRepo with JsonSupport with FileReader {
  val rules: Seq[DropQtyRule] = load()

  def get(customerId: Long): Seq[DropQtyRule] = {
    rules.filter(rule => rule.customerId == customerId)
  }

  private[repo] def load(): Seq[DropQtyRule] = {
    val str = readFile(filePath)
    val json = parseJsonStr(str)
    json.as[Seq[DropQtyRule]].getOrElse(throw new Exception(s"read json file: ${filePath} failed"))
  }
}
