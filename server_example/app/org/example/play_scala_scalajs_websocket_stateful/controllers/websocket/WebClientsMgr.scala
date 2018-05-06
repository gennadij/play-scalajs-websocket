package org.example.play_scala_scalajs_websocket_stateful.controllers.websocket

import play.api.libs.json.JsValue


import akka.actor._
import play.api.Logger

/**
 * Copyright (C) 2016 Gennadi Heimann genaheimann@gmail.com
 * 
 * Created by Gennadi Heimann on 10.07.2017
 */

case object Join
case object Leave

final case class ClientSentMessage(message: JsValue)

object WebClientsMgr {
  def props(): Props = Props(new WebClientsMgr)
}

class WebClientsMgr extends Actor {
  
  def receive = process(Set.empty)
  
  def process(subscribers: Set[ActorRef]): Receive = {
    case Join => 
      Logger.debug("WebClient Aktor Join")
      context become process(subscribers + sender)
    case Leave =>
      Logger.debug("WebClient Aktor Leave")
      context become process(subscribers - sender)
      
    case msg: ClientSentMessage =>
      Logger.debug("WebClient " + sender.hashCode() + " send message")
      sender ! msg
  }
  
}