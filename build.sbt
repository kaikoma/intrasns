name := "intrasns"

version := "1.0-SNAPSHOT"

scalaVersion := "2.10.2"

libraryDependencies ++= Seq(
  jdbc,
  anorm,
  cache,
  "org.pac4j" % "play-pac4j_scala" % "1.2.0",
  "org.pac4j" % "pac4j-http" % "1.5.0",
  "org.pac4j" % "pac4j-openid" % "1.5.0",
  "mysql" % "mysql-connector-java" % "5.1.30",
  "com.fasterxml.jackson.module" %% "jackson-module-scala" % "2.1.3"
)

resolvers ++= Seq("Local Maven Repository" at "file://"+Path.userHome.absolutePath+"/.m2/repository",
                "Sonatype snapshots repository" at "https://oss.sonatype.org/content/repositories/snapshots/")

play.Project.playScalaSettings
