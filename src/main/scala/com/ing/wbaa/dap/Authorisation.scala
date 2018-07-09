package com.ing.wbaa.dap

import scala.concurrent.Future

class Authorisation {
  def authorise: Future[Boolean] = Future.successful(true)
}
