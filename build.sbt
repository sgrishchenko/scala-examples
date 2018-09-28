import scalariform.formatter.preferences._

name := "scala-examples"

version := "0.1"

scalaVersion := "2.12.6"

libraryDependencies += "org.scala-lang.modules" %% "scala-xml" % "1.1.0"

libraryDependencies += "org.scalactic" %% "scalactic" % "3.0.1"
libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.1" % "test"

scalariformPreferences := scalariformPreferences.value
  .setPreference(DanglingCloseParenthesis, Force)
