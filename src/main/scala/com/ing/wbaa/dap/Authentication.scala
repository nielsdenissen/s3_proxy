package com.ing.wbaa.dap

import scala.concurrent.Future

class Authentication {
  def authenticate: Future[Boolean] = Future.successful(true)
}
