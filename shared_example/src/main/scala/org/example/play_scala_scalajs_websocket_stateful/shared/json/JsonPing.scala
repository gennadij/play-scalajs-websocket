package org.example.play_scala_scalajs_websocket_stateful.shared.json

import play.api.libs.json.Json

case class JsonPing (
    json: String = "ping", 
    params: JsonParams
)

object JsonPing {
  implicit val format = Json.format[JsonPing]
}
