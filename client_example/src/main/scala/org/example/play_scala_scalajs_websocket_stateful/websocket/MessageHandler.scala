package org.example.play_scala_scalajs_websocket_stateful.websocket

import play.api.libs.json.JsValue
import play.api.libs.json.Json
import play.api.libs.json.JsResult
import play.api.libs.json.JsSuccess
import play.api.libs.json.JsError
import org.scalajs.dom.raw.WebSocket
import play.api.libs.json.JsValue.jsValueToJsLookup
import play.api.libs.json.Json.toJsFieldJsValueWrapper
import org.example.play_scala_scalajs_websocket_stateful.shared.json.JsonPong
import org.example.play_scala_scalajs_websocket_stateful.pingPong.PingPong
import org.example.play_scala_scalajs_websocket_stateful.shared.json.JsonPing


/**
 * Copyright (C) 2016 Gennadi Heimann genaheimann@gmail.com
 * 
 * Created by Gennadi Heimann 25.04.2018
 */
class MessageHandler(websocket: WebSocket) {
  
  
  def handleMessage(receivedMessage: JsValue) = {
    (receivedMessage \ "json").asOpt[String] match {
      case Some("pong") => pingPong(receivedMessage)
      case _ => Json.obj("error" -> "keinen Treffer")
    }
  }
  
  private def pingPong(receivedMessage: JsValue) = {
    val jsonPong: JsResult[JsonPong] = Json.fromJson[JsonPong](receivedMessage)
    jsonPong match {
      case s: JsSuccess[JsonPong] => s.get
      case e: JsError => println("Errors -> PING_PONG: " + JsError.toJson(e).toString())
    }
    val jsonPing: String = Json.toJson(new PingPong(websocket).pingPong(jsonPong.get)).toString
    println("OUT -> " + jsonPing)
    websocket.send(jsonPing)
  }
}