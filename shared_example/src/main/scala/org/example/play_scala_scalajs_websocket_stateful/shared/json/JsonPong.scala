package org.example.play_scala_scalajs_websocket_stateful.shared.json

import play.api.libs.json.Json

case class JsonPong(
    json: String = "pong", 
    result: JsonResult
)

object JsonPong {
  implicit val format = Json.format[JsonPong]
}
