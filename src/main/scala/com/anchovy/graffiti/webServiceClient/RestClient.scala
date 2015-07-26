package com.anchovy.graffiti.webServiceClient

import java.io.IOException

import org.apache.http.client.methods.{CloseableHttpResponse, HttpGet}
import org.apache.http.impl.client.{CloseableHttpClient, HttpClients}
import org.apache.http.util.EntityUtils
import org.mashupbots.socko.infrastructure.Logger

/**
 * Created by iman on 05/06/15.
 */
class RestClient extends Logger {

  @throws[IOException]("if url thrown error")
  def Call(url: String): String = {

    val httpClient: CloseableHttpClient = HttpClients.createDefault()

    @volatile var httpGet: HttpGet = new HttpGet(url)
    httpGet.addHeader("accept", "application/json")
    val response: CloseableHttpResponse = httpClient.execute(httpGet)
    val responseBody = EntityUtils.toString(response.getEntity)

    responseBody
  }
}
