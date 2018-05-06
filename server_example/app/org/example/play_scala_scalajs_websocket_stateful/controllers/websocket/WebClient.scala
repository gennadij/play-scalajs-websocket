package org.example.play_scala_scalajs_websocket_stateful.controllers.websocket

/**
 * Copyright (C) 2016 Gennadi Heimann genaheimann@gmail.com
 * 
 * Created by Gennadi Heimann on 24.10.2017
 */
import play.api.libs.json.JsValue
import org.example.play_scala_scalajs_websocket_stateful.controllers.interfaceClient.MessageHandler
import org.example.play_scala_scalajs_websocket_stateful.controllers.interfaceClient.Server
import play.api.LoggerLike
import play.api.Logger

object WebClient {
  def init = {
    new WebClient
  }
}


class WebClient extends MessageHandler{
  val admin = new Server()
  
  def handleMessage(receivedMessage: JsValue): JsValue = {
    handleMessage(receivedMessage, admin)
  }
}