package mixin

trait FileReader {
  def readFile(filePath: String): String = {
    val source = scala.io.Source.fromFile(filePath, "UTF-8")
    try source.mkString finally source.close()
  }
}
