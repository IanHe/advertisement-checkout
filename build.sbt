name := "seek-ads-checkout"

version := "0.1"

scalaVersion := "2.13.6"

mainClass in (Compile, run) := Some("LocalMain")

libraryDependencies ++= Seq(
  library.circeGenericExtras,
  library.circeParser,
  library.config) ++
  testDependencies

val testDependencies = Seq(
  library.scalaTest % "test",
  library.mockitoScala % "test",
  library.mockitoScalaScalatest % "test")

lazy val library =
  new {
    object Version {
      val circe = "0.13.0"
      val config = "1.4.1"
      val scalaTest = "3.2.9"
      val mockitoScala = "1.7.1"
    }

    val circeGenericExtras = "io.circe" %% "circe-generic-extras" % Version.circe
    val circeParser = "io.circe" %% "circe-parser" % Version.circe
    val config = "com.typesafe" % "config" % Version.config
    val scalaTest = "org.scalatest" %% "scalatest" % Version.scalaTest
    val mockitoScala = "org.mockito" %% "mockito-scala" % Version.mockitoScala
    val mockitoScalaScalatest = "org.mockito" %% "mockito-scala-scalatest" % Version.mockitoScala
  }