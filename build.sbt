name := "spark-twitter-sentiment"

version := "1.0"

scalaVersion := "2.11.8"

libraryDependencies += "edu.stanford.nlp" % "stanford-corenlp" % "3.5.1"
libraryDependencies += "edu.stanford.nlp" % "stanford-corenlp" % "3.5.1" classifier "models"
libraryDependencies += "org.apache.spark" %% "spark-core" % "2.1.0"