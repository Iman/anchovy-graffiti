import sbtassembly.Plugin.AssemblyKeys._

packageArchetype.java_application

name := "anchovy-graffiti"

version := "1.4"

scalaVersion := "2.11.6"

libraryDependencies ++= Seq(
  "org.mashupbots.socko" % "socko-webserver_2.11" % "0.6.0",
  "com.google.code.gson" % "gson" % "2.3.1",
  "org.apache.httpcomponents" % "httpclient" % "4.4.1",
  "org.apache.httpcomponents" % "httpcore" % "4.4.1",
  "commons-codec" % "commons-codec" % "1.10",
  "commons-logging" % "commons-logging" % "1.2",
  "org.scalatest" %% "scalatest" % "2.2.0" % "test",
  "junit" % "junit" % "4.12",
  "org.mockito" % "mockito-core" % "1.8.5"
)

assemblySettings

mainClass in assembly := Some("com.anchovy.graffiti.Application")