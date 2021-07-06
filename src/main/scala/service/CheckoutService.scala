package service

import domain.CheckoutRequest

trait CheckoutService {
  def handleRequest(request: CheckoutRequest): BigDecimal
}


