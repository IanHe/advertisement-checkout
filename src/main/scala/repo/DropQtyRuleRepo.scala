package repo

import domain.DropQtyRule

trait DropQtyRuleRepo {
  def get(customerId: Long): Seq[DropQtyRule]
}