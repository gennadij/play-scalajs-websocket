package org.example.play_scala_scalajs_websocket_stateful.shared.json

import play.api.libs.json.Json

/**
 * Copyright (C) 2016 Gennadi Heimann genaheimann@gmail.com
 * 
 * Created by Gennadi Heimann 19.12.2016
 */

case class JsonResult (
    pong: String 
)

object JsonResult {
  implicit val format = Json.format[JsonResult]
}