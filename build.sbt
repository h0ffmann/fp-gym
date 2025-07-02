val scala3Version = "3.7.1"

lazy val root = project
  .in(file("."))
  .settings(
    name := "fp-gym",
    version := "0.1.0-SNAPSHOT",

    scalaVersion := scala3Version,

    // Compiler options
    scalacOptions ++= Seq(
      "-deprecation",
      "-unchecked", 
      "-feature"
    ),
    
    // Scalafmt settings
    scalafmtOnCompile := true,

    libraryDependencies ++= Seq(
      "org.scalameta" %% "munit" % "1.0.4" % Test,
      "org.scalacheck" %% "scalacheck" % "1.18.1" % Test,
      "org.scalameta" %% "munit-scalacheck" % "1.0.0" % Test
    )

    
  )
