import com.typesafe.config.Config
import domain.CheckoutRequest
import externalresource.ExternalResourceBuilder
import mixin.FileReader
import mixin.JsonSupport
import service.ServiceAssembler

import scala.io.StdIn.readLine

class Bootstrap(config: Config) extends JsonSupport with FileReader {

  def run(): Unit = {
    val resource = new ExternalResourceBuilder(config).build()
    val serviceAssembler = new ServiceAssembler(resource)
    val service = serviceAssembler.getCheckoutService()

    while (true) {
      println("please input the CheckoutRequest file path:")
      val filePath = readLine()
      if ("exit" == filePath.trim) System.exit(0)
      try {
        val jsonStr = readFile(filePath)
        val request = parseJsonStr(jsonStr).as[CheckoutRequest].getOrElse(throw new Exception("invalid check out request, please check your file path and json format"))
        val amount = service.handleRequest(request)
        println(s"Total: ${amount}")
      } catch {
        case e: Exception => println(e)
      }
    }
  }
}
