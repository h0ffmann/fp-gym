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

    libraryDependencies += "org.scalameta" %% "munit" % "1.0.4" % Test
  )
