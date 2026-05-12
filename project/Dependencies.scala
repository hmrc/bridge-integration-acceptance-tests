import sbt.*
import sbt.Keys.libraryDependencies

object Dependencies {

  val test: Seq[ModuleID] = Seq(
    "uk.gov.hmrc"                %% "api-test-runner"         % "0.9.0"  % Test,
    "com.vladsch.flexmark"        % "flexmark-all"            % "0.64.8" % Test,
    "com.typesafe"                % "config"                  % "1.4.7"  % Test,
    "org.slf4j"                   % "slf4j-simple"            % "2.0.17" % Test,
    "org.playframework"          %% "play-json"               % "3.0.6"  % Test,
    "org.playframework"          %% "play-ahc-ws-standalone"  % "3.0.10" % Test,
    "org.playframework"          %% "play-ws-standalone-json" % "3.0.10" % Test,
    "org.playframework"          %% "play-ws-standalone-xml"  % "3.0.10" % Test,
    "org.scalatest"              %% "scalatest"               % "3.2.20" % "test",
    "com.typesafe.scala-logging" %% "scala-logging"           % "3.9.6"  % Test,
    "org.apache.pekko"           %% "pekko-http"              % "1.3.0"  % Test,
    "org.apache.pekko"           %% "pekko-stream"            % "1.6.0"  % Test,
    "com.github.mifmif"           % "generex"                 % "1.0.2",
    "com.google.zxing"            % "core"                    % "3.5.4"  % Test,
    "com.google.zxing"            % "javase"                  % "3.5.4"  % Test,
    "commons-io"                  % "commons-io"              % "2.22.0" % Test,
    "com.beachape"               %% "enumeratum-play-json"    % "1.9.7"  % Test,
    "com.daodecode"              %% "scalaj-collection"       % "0.3.2"  % Test,
    "org.scalameta"              %% "scalameta"               % "4.16.1" % Test,
    "com.github.scopt"           %% "scopt"                   % "4.1.0"  % Test,
    "com.fasterxml.jackson.core"  % "jackson-core"            % "2.21.3" % Test,
    "ch.qos.logback"              % "logback-core"            % "1.5.32" % Test
  )

}
