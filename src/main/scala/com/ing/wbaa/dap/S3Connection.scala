package com.ing.wbaa.dap

import com.amazonaws.{ClientConfiguration, Protocol}
import com.amazonaws.auth.BasicAWSCredentials
import com.amazonaws.client.builder.AwsClientBuilder.EndpointConfiguration
import com.amazonaws.services.s3.{AmazonS3Client, AmazonS3ClientBuilder}
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
  val s3conn = new S3Connection("127.0.0.1", 8080)
  val buckets = s3conn.conn.listBuckets

  buckets.map {
    bucket =>
      println(bucket.getName + "\t" + StringUtils.fromDate(bucket.getCreationDate))
  }

}
