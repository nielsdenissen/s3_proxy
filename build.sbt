import scalariform.formatter.preferences._

name := "s3_proxy"

version := "0.1"

scalaVersion := "2.12.6"

enablePlugins(JavaAppPackaging)

resolvers ++= Seq(
  Resolver.bintrayRepo("cakesolutions", "maven"),
  Resolver.jcenterRepo
)

libraryDependencies ++= Seq(
  "com.typesafe.akka"     %% "akka-actor"                  % "2.5.13",
  "com.typesafe.akka"     %% "akka-stream"                 % "2.5.13",
  "com.typesafe.akka"     %% "akka-http"                   % "10.1.3",
  "com.typesafe.akka"     %% "akka-http-testkit"           % "10.1.3"        % Test,
//  "com.typesafe.akka"     %% "akka-http-spray-json"        % "10.1.3"       % "test",
  "org.scalatest"         %% "scalatest"                   % "3.0.1"         % "test"
)

//libraryDependencies += "com.criteo.lolhttp" %% "lolhttp" % "0.10.1"
//libraryDependencies += "org.apache.ranger" % "ranger-plugins-common" % "1.0.0"
//libraryDependencies += "io.lemonlabs" %% "scala-uri" % "1.1.2"
//libraryDependencies += "org.apache.logging.log4j" %% "log4j-api-scala" % "11.0"
//libraryDependencies += "io.github.twonote" % "radosgw-admin4j" % "1.1.0"
//libraryDependencies += "io.monix" %% "monix" % "3.0.0-RC1"

scalariformPreferences := scalariformPreferences.value
  .setPreference(AlignSingleLineCaseStatements, true)
  .setPreference(DoubleIndentConstructorArguments, true)
  .setPreference(DanglingCloseParenthesis, Preserve)
