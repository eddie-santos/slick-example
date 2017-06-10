name := "slick-example"

version := "1.0"

scalaVersion := "2.12.2"

libraryDependencies ++= Seq(
  "org.postgresql" % "postgresql" % "42.1.1",
  "com.typesafe.slick" % "slick_2.12" % "3.2.0",
  "com.typesafe.slick" %% "slick-hikaricp" % "3.2.0",
  "com.typesafe" % "config" % "1.3.1",
  "org.slf4j" % "slf4j-nop" % "1.7.25",
  "com.github.tototoshi" % "scala-csv_2.12" % "1.3.4"
)
        