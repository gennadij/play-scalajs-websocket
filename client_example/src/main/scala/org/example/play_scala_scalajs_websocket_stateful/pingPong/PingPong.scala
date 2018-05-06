package org.example.play_scala_scalajs_websocket_stateful.pingPong

import org.example.play_scala_scalajs_websocket_stateful.shared.json.JsonPing
import org.example.play_scala_scalajs_websocket_stateful.shared.json.JsonParams
import org.scalajs.dom.raw.WebSocket
import org.example.play_scala_scalajs_websocket_stateful.shared.json.JsonPong
import org.scalajs.jquery.jQuery

/**
 * Copyright (C) 2016 Gennadi Heimann genaheimann@gmail.com
 * 
 * Created by Gennadi Heimann 04.05.2018
 */
class PingPong(websocket: WebSocket) {
  
  def pingPong(jsonPong: JsonPong): JsonPing = {
    
    val jsonPing = JsonPing(
        params = JsonParams(
            "ping"
        )
    )
    
    val html = 
      s"<div id='text'>" +
        "<p> IN -> " + jsonPong.result.pong + "</p>" +
        "<p> OUT -> " + jsonPing.params.ping + "</p>" +
      "</div>"
    
    
    jQuery(html).appendTo(jQuery("body"))
    jsonPing
  }
}