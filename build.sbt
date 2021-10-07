name := "sample-project"
version := "0.1"
organization := "com.example"

scalaVersion := "2.13.6"

val SlickVersion = "3.3.3"
val Slf4jVersion = "2.0.0-alpha5"

// Slick
libraryDependencies ++= Seq(
  "com.typesafe.slick" %% "slick" % SlickVersion,
  "com.typesafe.slick" %% "slick-hikaricp" % SlickVersion)

// MySql
libraryDependencies += "mysql" % "mysql-connector-java" % "8.0.26"

// Logging
libraryDependencies ++= Seq(
  "org.slf4j" % "slf4j-api" % Slf4jVersion,
  "org.slf4j" % "slf4j-log4j12" % Slf4jVersion)

libraryDependencies += "io.spray" %% "spray-json" % "1.3.6"
