package repo

import domain.Customer
import spec.UnitSpec

class CustomerRepoFromJsonSpec extends UnitSpec {
  trait Fixture {
    val filePath = "src/main/resources/db/customer.db.json"
    val customerRepo = new CustomerRepoFromJson(filePath)
    val expectedCustomer: Customer = Customer(0, "default")
  }

  "test load json from file" in new Fixture {
    val customers = customerRepo.load()
    customers.size shouldBe 4
    customers.head shouldBe expectedCustomer
  }

  "test get customer by id" should {
    "get customer when customer id exists" in new Fixture {
      customerRepo.load()
      customerRepo.get(0) shouldBe Some(expectedCustomer)
    }
    "get None when customer id does not exist" in new Fixture {
      customerRepo.load()
      customerRepo.get(100) shouldBe None
    }
  }
}
