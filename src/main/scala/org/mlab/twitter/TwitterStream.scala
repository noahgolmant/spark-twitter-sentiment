package org.mlab.twitter

import org.apache.spark._
import org.apache.spark.streaming._
import org.apache.spark.streaming.dstream.DStream
import twitter4j.Status
import org.apache.spark.streaming.twitter.TwitterUtils

import scala.collection.JavaConversions._

/**
  * Created by ravi on 2/22/17.
  */
class TwitterStream {

  def getDStream(): DStream[Status] = {
    val sc = new SparkContext(new SparkConf().setAppName("stream-comp-twitter-stream-sentiment"))

    sc.setLogLevel("WARN")

    val ssc = new StreamingContext(sc, Seconds(5))

    println("Initializing Twitter stream...")

    System.setProperty("twitter4j.oauth.consumerKey", "yy80mXCK2VV12LxxEVhQfrsTx")
    System.setProperty("twitter4j.oauth.consumerSecret", "QS518cO564jeeq5zFEg0r3UWKMOjlq4J61kC0Ny4jMN8GKh0MA")
    System.setProperty("twitter4j.oauth.accessToken", "3317348143-DkJvR5VD3DTuXb4n8MkVJT9MPBLpjRqpd5Rm3tw")
    System.setProperty("twitter4j.oauth.accessTokenSecret", "mJdZVyQxuublUL2y7Rwj7tNBIRJLoH5GoSavbjFI9bke0")

    val stream = TwitterUtils.createStream(ssc, None)

    return stream
  }
}
