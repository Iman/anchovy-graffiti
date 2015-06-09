package com.anchovy.graffiti.WebServiceClient

import com.anchovy.graffiti.webServiceClient._
import org.mashupbots.socko.infrastructure.Logger
import org.mockito.Mockito._
import org.scalatest.{BeforeAndAfter, FunSuite}
import org.scalatest.mock.MockitoSugar

/**
 * Created by iman on 08/06/15.
 */
class RestClientTest  extends FunSuite with BeforeAndAfter with MockitoSugar with Logger {

  test("Rest Client") {

    trait MockRestClient {

      val restClient = mock[RestClient.type ]
      val dummyURL = "http://localhost/dummy/foo/bar&time=54645763836"
      when(restClient.Call(dummyURL)).thenReturn("responseBody")
      when(restClient.Call("localhost")).thenReturn("{'foo':'bar'}")

      val dummyRestClientA = restClient.Call(dummyURL)
      val dummyRestClientB = restClient.Call("localhost")

      assert(dummyRestClientA == "responseBody")
      assert(dummyRestClientB.contains("{'foo':'bar'}"))
    }

  }
}