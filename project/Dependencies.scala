import sbt._

object Dependencies {
  object Version {
    val scalaTest = "3.0.6-SNAP5"
  }

  object Test {
    val scalaTest = "org.scalatest" %% "scalatest" % Version.scalaTest % "test"
  }
}
