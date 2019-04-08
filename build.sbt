organization := "ca.mrvisser"

name := "sealerate"

licenses += ("Apache-2.0", url("http://opensource.org/licenses/Apache-2.0"))

homepage := Some(url("https://github.com/mrvisser/sealerate"))

pomExtra := {
  <scm>
    <url>git@github.com:mrvisser/sealerate.git</url>
    <connection>scm:git:git@github.com:mrvisser/sealerate.git</connection>
  </scm>
  <developers>
    <developer>
      <id>mrvisser</id>
      <name>Branden Visser</name>
      <url>https://github.com/mrvisser</url>
    </developer>
  </developers>
}

scalaVersion := "2.12.7"

crossScalaVersions := Seq("2.10.7", "2.11.12", "2.12.7", "2.13.0-RC1")

scalacOptions ++= Seq(
  "-Xlint",
  "-deprecation",
  "-feature",
  "-unchecked",
  "-language:higherKinds",
  "-language:experimental.macros"
)

resolvers ++= Seq(
  Resolver.sonatypeRepo("releases"),
  Resolver.sonatypeRepo("snapshots")
)

libraryDependencies += scalaVersion("org.scala-lang" % "scala-compiler" % _).value

libraryDependencies ++= Seq(
  Dependencies.Test.scalaTest
)

unmanagedSourceDirectories in Compile += (sourceDirectory in Compile).value / s"scala_${scalaBinaryVersion.value}"
