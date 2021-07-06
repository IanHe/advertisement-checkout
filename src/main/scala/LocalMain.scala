import com.typesafe.config.ConfigFactory

object LocalMain {
  def main(args: Array[String]): Unit = {
    val config = ConfigFactory.parseResources("application.conf")
    new Bootstrap(config).run()
  }
}