package com.ing.wbaa.dap

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.model._
import akka.stream.ActorMaterializer
import akka.stream.scaladsl.Sink

import scala.concurrent.Future

object Main extends App {
  implicit val system = ActorSystem()
  implicit val materializer = ActorMaterializer()
  implicit val executionContext = system.dispatcher

  val serverSource = Http().bind(interface = "localhost", port = 8081)

  val authorisation = new Authorisation
  val authentication = new Authentication

  val requestHandler: HttpRequest => Future[HttpResponse] = htr =>
    authentication.authenticate
      .flatMap {
        case false => Future(HttpResponse(StatusCodes.ProxyAuthenticationRequired))
        case true => authorisation.authorise
      }
      .flatMap {
        case false => Future(HttpResponse(StatusCodes.Unauthorized))
        case true =>
          println(s"OLD: $htr")
          val newHtr = htr.copy(uri = htr.uri.withAuthority("localhost", 8010))
          println(s"NEW: $newHtr")

          Http().singleRequest(newHtr)
      }

  val bindingFuture: Future[Http.ServerBinding] = {
    println("Server has started")
    serverSource.to(Sink.foreach { connection =>
      println("Accepted new connection from " + connection.remoteAddress)

      connection handleWithAsyncHandler requestHandler
      // this is equivalent to
      // connection handleWith { Flow[HttpRequest] map requestHandler }
    }).run()
  }
}
