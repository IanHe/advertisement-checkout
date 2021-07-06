package repo

import domain.DropPriceRule

trait DropPriceRuleRepo {
  def get(customerId: Long): Seq[DropPriceRule]
}