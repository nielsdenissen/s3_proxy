package com.ing.wbaa.dap

import com.amazonaws.{ClientConfiguration, Protocol}
import com.amazonaws.auth.BasicAWSCredentials
import com.amazonaws.services.s3.AmazonS3Client
import com.amazonaws.util.StringUtils

import scala.collection.JavaConversions._

class S3Connection(host: String, port: Int) {
  val accessKey = "accesskey"
  val secretKey = "secretkey"

  val credentials = new BasicAWSCredentials(accessKey, secretKey)

  val clientConfig = new ClientConfiguration
  clientConfig.setProtocol(Protocol.HTTP)

  val conn = new AmazonS3Client(credentials, clientConfig)
  conn.setEndpoint(s"$host:$port")
}

object Main extends App {
  val s3conn = new S3Connection("localhost", 8010)
  val buckets = s3conn.conn.listBuckets

  buckets.map {
    bucket =>
      println(bucket.getName + "\t" + StringUtils.fromDate(bucket.getCreationDate))
  }

}
