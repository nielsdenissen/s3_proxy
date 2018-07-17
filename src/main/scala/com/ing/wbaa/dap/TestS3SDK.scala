package com.ing.wbaa.dap

import java.io.File

import com.amazonaws.ClientConfiguration
import com.amazonaws.auth.{AWSStaticCredentialsProvider, BasicAWSCredentials}
import com.amazonaws.client.builder.AwsClientBuilder.EndpointConfiguration
import com.amazonaws.services.s3.AmazonS3ClientBuilder
import com.amazonaws.services.s3.model.AmazonS3Exception

object TestS3SDK {
  def main(args: Array[String]): Unit = {

    val bucketName = "demobucket"
    //    val testUsrCredentials = new BasicAWSCredentials("0X9PYF3W47E83S94KZY7", "33RDNoMx22NXEbtk3NCikd3d22eYAx8CxwLO9wm3")
    val testUsrCredentials = new BasicAWSCredentials("accesskey", "secretkey")

    val cliConf = new ClientConfiguration()
    cliConf.setMaxErrorRetry(1)
//    cliConf.setSignerOverride("AWS4SignerType")

    val testCli = AmazonS3ClientBuilder
      .standard()
      .withClientConfiguration(cliConf)
      .withCredentials(new AWSStaticCredentialsProvider(testUsrCredentials))
      .withEndpointConfiguration(new EndpointConfiguration("http://127.0.0.1:8080", "us-west-2"))
      .build()


    try {
      //            val buckets = testCli.listBuckets()
      //            println(buckets)
      val objects = testCli.listObjectsV2(bucketName)
      println(objects.getObjectSummaries)
      //testCli.getObject(bucketName, "cmder.zip")
      // get other stuff
      //          testCli.putObject(bucketName, "cmder.zip", new File("c:\\Install\\cmder.zip"))

      //        println( " \n " + resp.getMetadata.toString)
    } catch {
      case e: AmazonS3Exception => println("Not allowed " + e.getMessage)
    }
  }
}
