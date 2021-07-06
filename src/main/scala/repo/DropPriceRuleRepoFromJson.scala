package repo

import domain.DropPriceRule
import mixin.FileReader
import mixin.JsonSupport

class DropPriceRuleRepoFromJson(filePath: String) extends DropPriceRuleRepo with FileReader with JsonSupport {
  val rules: Seq[DropPriceRule] = load()

  def get(customerId: Long): Seq[DropPriceRule] = {
    rules.filter(rule => rule.customerId == customerId)
  }

  private[repo] def load(): Seq[DropPriceRule] = {
    val str = readFile(filePath)
    val json = parseJsonStr(str)
    json.as[Seq[DropPriceRule]].getOrElse(throw new Exception(s"read json file: ${filePath} failed"))
  }
}
