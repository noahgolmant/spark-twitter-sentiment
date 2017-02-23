package org.mlab.twitter
import org.apache.spark._
import org.apache.spark.streaming._
import org.apache.spark.streaming.dstream.DStream
import twitter4j.Status

/**
  * Created by ravi on 2/22/17.
  */
class SentimentAnalysis(tweetStream: DStream[Status]) {

}
