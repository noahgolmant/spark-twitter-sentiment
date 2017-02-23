package org.mlab.twitter
import java.util.Properties

import edu.stanford.nlp.ling.CoreAnnotations
import edu.stanford.nlp.neural.rnn.RNNCoreAnnotations
import edu.stanford.nlp.pipeline.StanfordCoreNLP
import edu.stanford.nlp.sentiment.SentimentCoreAnnotations
import org.apache.spark._
import org.apache.spark.streaming._
import org.apache.spark.streaming.dstream.DStream
import twitter4j.Status

import scala.collection.JavaConversions._

/**
  * Created by ravi on 2/22/17.
  */
class SentimentAnalysis(tweetStream: DStream[Status]) {
  val props = new Properties()
  props.setProperty("annotators", "tokenize, ssplit, parse, sentiment")
  val pipeline = new StanfordCoreNLP(props)

  private def getSentiment(str: String): Int = {
    val annotation = pipeline.process(str)
    val sentence = annotation.get(classOf[CoreAnnotations.SentencesAnnotation])
      .maxBy(coreMap => coreMap.toString.length)
    val tree = sentence.get(classOf[SentimentCoreAnnotations.AnnotatedTree])
    val sentiment = RNNCoreAnnotations.getPredictedClass(tree)
    println("Tweet text: " + sentence.toString)
    println("Tweet sentiment: " + sentiment)
    sentiment
  }

  val tweetSentiments = tweetStream.map(status => getSentiment(status.getText))
}
