package org.example.play_scala_scalajs_websocket_stateful.controllers.interfaceClient

import play.api.libs.json._
import play.api.Logger
import play.api.libs.json.JsValue.jsValueToJsLookup
import play.api.libs.json.Json.toJsFieldJsValueWrapper
import org.example.play_scala_scalajs_websocket_stateful.shared.json.JsonPing
import org.example.play_scala_scalajs_websocket_stateful.shared.json.JsonPong

/**
 * Copyright (C) 2016 Gennadi Heimann genaheimann@gmail.com
 * 
 * Created by Gennadi Heimann 28.11.2016
 */

trait MessageHandler {
  
  var counter = 0
  
  def handleMessage(receivedMessage: JsValue, server: Server): JsValue = {
    (receivedMessage \ "json").asOpt[String] match {
      case Some("ping") => pingPong(receivedMessage, server)
      case _ => Json.obj("error" -> "keinen Treffer")
    }
  }

  private def pingPong(receivedMessage: JsValue, server: Server): JsValue = {
    val jsonPing: JsResult[JsonPing] = Json.fromJson[JsonPing](receivedMessage)
    jsonPing match {
      case s : JsSuccess[JsonPing] => s.get
      case e : JsError => Logger.error("Errors -> REGISTRATION: " + JsError.toJson(e).toString())
    }
    val jsonPong: JsonPong = 
      server.pingPong(jsonPing.get)
    Logger.info("counter " + counter)
    if (counter < 3) {
      counter = counter + 1
      Json.toJson(jsonPong)
    } else {
      Json.toJson("")
    }
  }
}
