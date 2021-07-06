package repo

import domain.Customer

trait CustomerRepo {
  def get(id: Long): Option[Customer]
}