package com.anchovy.graffiti.webSocketServer

import akka.actor.Actor
import akka.event.slf4j.SLF4JLogging
import com.anchovy.graffiti.Application
import com.anchovy.graffiti.webServiceClient.YahooWeatherService
import com.google.gson.{JsonElement, JsonParser}
import org.mashupbots.socko.events.WebSocketFrameEvent


/**
 * Created by iman on 05/06/15.
 */

class WebSocketHandler extends Actor with SLF4JLogging {

  def receive = {
    case event: WebSocketFrameEvent =>
      writeWebSocketResponse(event)
      context.stop(self)
    case _ => {
      log.info("received unknown message of type: ")
      context.stop(self)
    }

  }

  def writeWebSocketResponse(event: WebSocketFrameEvent) {

    val jsonElement: JsonElement = new JsonParser().parse(event.readText)

    if (jsonElement.isJsonObject()) {

      val json = jsonElement.getAsJsonObject()
      val response = YahooWeatherService.apiCall(json)

      Application.webServer.webSocketConnections.writeText(response, event.webSocketId)

    } else {

      //Comment out this line when using Autobahn WebSockets Testsuite
      Application.webServer.webSocketConnections.writeText("json object is missing")

      //Use this line to work with fuzzing client (Autobahn WebSockets Testsuite @see http://autobahn.ws/testsuite/)
      //Application.webServer.webSocketConnections.writeText(event.readText)
    }
  }

}