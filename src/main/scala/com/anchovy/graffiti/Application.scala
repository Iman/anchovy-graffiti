package com.anchovy.graffiti

import akka.actor.{ActorSystem, Props, actorRef2Scala}
import com.anchovy.graffiti.webSocketServer.WebSocketHandler
import org.mashupbots.socko.infrastructure.Logger
import org.mashupbots.socko.routes._
import org.mashupbots.socko.webserver.{WebServer, WebServerConfig}

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

  val webServer = new WebServer(WebServerConfig(serverName = "AnchovyGraffiti Server", hostname = "0.0.0.0", port = 8080), routes, actorSystem)

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