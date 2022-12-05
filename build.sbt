val scala3Version = "3.2.1"

lazy val root = project
  .in(file("."))
  .settings(
    name := "aoc-22",
    version := "0.1.0-SNAPSHOT",

    scalaVersion := scala3Version,

    libraryDependencies ++= Seq("org.scalameta" %% "munit" % "0.7.29" % Test,
      "org.scala-lang.modules" %% "scala-parser-combinators" % "2.1.1"),
  )
