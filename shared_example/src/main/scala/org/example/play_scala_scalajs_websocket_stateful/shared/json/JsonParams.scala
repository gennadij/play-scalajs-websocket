package org.example.play_scala_scalajs_websocket_stateful.shared.json

import play.api.libs.json.Json

case class JsonParams (
    ping: String
) 


object JsonParams {
  implicit val format = Json.format[JsonParams]
}