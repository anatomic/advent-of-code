val scala3Version = "3.2.1"

val munit = "org.scalameta" %% "munit" % "1.0.0-M4"
val parserCombinators =
  "org.scala-lang.modules" %% "scala-parser-combinators" % "2.1.1"

enablePlugins(GraalVMNativeImagePlugin)

lazy val root = project
  .in(file("."))
  .settings(
    name := "aoc-22",
    version := "0.1.0-SNAPSHOT",
    scalaVersion := scala3Version,
    libraryDependencies ++= Seq(munit, parserCombinators),
    graalVMNativeImageOptions ++= Seq(
      "--no-fallback"
    )
  )
