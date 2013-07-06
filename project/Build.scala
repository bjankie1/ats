import sbt._
import Keys._
import PlayProject._

object ApplicationBuild extends Build {

  val appName = "ats"
  val appVersion = "1.0-SNAPSHOT"

  val appDependencies = Seq(
//    "org.scala-tools.testing" %% "specs" % "1.6.9" % "test"
    "com.github.nscala-time" %% "nscala-time" % "0.4.2"
  )

  val main = play.Project(appName, appVersion, appDependencies).settings(
      // Add your own project settings here
      testOptions in Test := Nil
  )

}
