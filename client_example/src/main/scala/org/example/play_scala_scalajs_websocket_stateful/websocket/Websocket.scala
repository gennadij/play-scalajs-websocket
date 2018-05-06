package org.example.play_scala_scalajs_websocket_stateful.websocket


import org.scalajs.dom
import scala.scalajs._
import play.api.libs.json.JsValue
import play.api.libs.json.Json
import org.example.play_scala_scalajs_websocket_stateful.shared.json.JsonParams
import org.example.play_scala_scalajs_websocket_stateful.shared.json.JsonPing

object Websocket {
  val url = "ws://localhost:9000/example"
  val socket = new dom.WebSocket(url)
  val numPattern = "[0-9]+".r
  
  def main(args: Array[String]): Unit = {
    println("main")
	socket.onmessage = {
      (e: dom.MessageEvent) => {
        println("IN -> " + e.data.toString())
        val jsValue: JsValue = Json.parse(e.data.toString())
        new MessageHandler(socket).handleMessage(jsValue)
      }
    }
    
    socket.onopen = { (e: dom.Event) => {
        println("Websocket open")
        val jsonPing = Json.toJson(
            JsonPing(
                params = JsonParams(
                  "ping"
                )
            )
        )
        
        socket.send(jsonPing.toString())
      }
    }
    
    socket.onerror = {
      (e: dom.Event) => {
        println("Websocket error")
      }
    }
  }
}