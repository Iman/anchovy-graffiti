package com.anchovy.graffiti.WebSocketServer

import com.anchovy.graffiti.webSocketServer.WebSocketHandler
import org.mashupbots.socko.events.WebSocketFrameEvent
import org.mashupbots.socko.infrastructure.Logger
import org.mockito.Mockito._
import org.scalatest.mock.MockitoSugar
import org.scalatest.{BeforeAndAfter, FunSuite}

/**
 * Created by iman on 08/06/15.
 */
class WebSocketHandlerTest  extends FunSuite with BeforeAndAfter with MockitoSugar with Logger {

  test("Web Socket Handler") {

    trait MockWebSocketHandler {

        def event: WebSocketFrameEvent

        val mockWebSocketHandler = mock[WebSocketHandler]
        when(mockWebSocketHandler.receive.isDefinedAt(event)).thenCallRealMethod()
        when(mockWebSocketHandler.receive.isDefinedAt(event)).thenCallRealMethod()
        when(mockWebSocketHandler.writeWebSocketResponse(event: WebSocketFrameEvent)).thenReturn(event.writeText("Dummy Text"))
        val mockWriteWebSocketResponse = mockWebSocketHandler.writeWebSocketResponse(event: WebSocketFrameEvent)
        assert(mockWebSocketHandler.receive == event)
        assert(mockWebSocketHandler.writeWebSocketResponse(event) == event)

    }

  }
}
