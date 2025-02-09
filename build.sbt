ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "3.3.5"

lazy val root = (project in file("."))
  .settings(
    name := "ToDoAppCLI",
    idePackagePrefix := Some("com.app.stone.dev")
  )
