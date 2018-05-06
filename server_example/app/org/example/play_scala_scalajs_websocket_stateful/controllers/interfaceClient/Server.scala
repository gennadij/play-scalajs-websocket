package org.example.play_scala_scalajs_websocket_stateful.controllers.interfaceClient

import org.example.play_scala_scalajs_websocket_stateful.shared.json.JsonPing
import org.example.play_scala_scalajs_websocket_stateful.shared.json.JsonPong
import org.example.play_scala_scalajs_websocket_stateful.shared.json.JsonResult


class Server {
  
  
  def pingPong(jsonPing: JsonPing): JsonPong = {
    JsonPong(
        result = JsonResult(
            "pong"
        )
    )
  }
}