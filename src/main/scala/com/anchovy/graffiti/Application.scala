package com.anchovy.graffiti

import akka.actor.{ActorSystem, Props, actorRef2Scala}
import org.mashupbots.socko.infrastructure.Logger
import org.mashupbots.socko.routes._
import org.mashupbots.socko.webserver.{WebServer, WebServerConfig}
import com.anchovy.graffiti.webSocketServer.WebSocketHandler

/**
 * Created by iman on 07/06/15.
 */

object Application extends Logger {

  val actorSystem = ActorSystem("AnchovyGraffiti")

  val routes = Routes({

    case WebSocketHandshake(wsHandshake) => wsHandshake match {
      case Path("/") => {
        // Authorize the handshake.
        wsHandshake.authorize(
          onComplete = Some(onWebSocketHandshakeComplete),
          onClose = Some(onWebSocketClose))
      }
    }

    case WebSocketFrame(wsFrame) => {
      // Process frames sent from the client after successful handshaking
      actorSystem.actorOf(Props[WebSocketHandler]) ! wsFrame

    }

  })

  val webServer = new WebServer(WebServerConfig(serverName= "AnchovyGraffiti Server",port = 5000), routes, actorSystem)
  def main(args: Array[String]) {
    Runtime.getRuntime.addShutdownHook(new Thread {
      override def run {
        webServer.stop()
      }
    })
    webServer.start()
  }

  def onWebSocketHandshakeComplete(webSocketId: String) {
    System.out.println(s"Web Socket $webSocketId connected")
  }

  def onWebSocketClose(webSocketId: String) {
    System.out.println(s"Web Socket $webSocketId closed")
  }

}