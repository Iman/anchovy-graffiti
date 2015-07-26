package com.anchovy.graffiti.WebServiceClient

import com.anchovy.graffiti.webServiceClient._
import org.apache.http.client.ClientProtocolException
import org.mashupbots.socko.infrastructure.Logger
import org.mockito.Mockito._
import org.scalatest.mock.MockitoSugar
import org.scalatest.{BeforeAndAfter, FunSuite}

/**
 * Created by iman on 08/06/15.
 */
class RestClientTest extends FunSuite with BeforeAndAfter with MockitoSugar with Logger {

  val restClient = new RestClient

  test("Rest client mock test") {

    trait MockRestClient {

      val restClient = mock[RestClient]
      val dummyURL = "http://localhost/dummy/foo/bar&time=54645763836"
      when(restClient.Call(dummyURL)).thenReturn("responseBody")
      when(restClient.Call("localhost")).thenReturn("{'foo':'bar'}")

      val dummyRestClientA = restClient.Call(dummyURL)
      val dummyRestClientB = restClient.Call("localhost")

      assert(dummyRestClientA == "responseBody")
      assert(dummyRestClientB.contains("{'foo':'bar'}"))
    }

  }

  test("Get URL with ClientProtocolException") {

    intercept[ClientProtocolException] {
      restClient.Call("localhost")
    }

  }

  test("Test get URL") {

    /**
     * @see http://jsonplaceholder.typicode.com/
     */
    val response = restClient.Call("http://jsonplaceholder.typicode.com/users")
    assert(response.size != 0)

  }

}