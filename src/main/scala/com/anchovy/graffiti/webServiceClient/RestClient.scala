package com.anchovy.graffiti.webServiceClient

import org.apache.http.client.methods.{CloseableHttpResponse, HttpGet}
import org.apache.http.impl.client.{CloseableHttpClient, HttpClients}
import org.apache.http.util.EntityUtils
import org.mashupbots.socko.infrastructure.Logger

/**
 * Created by iman on 05/06/15.
 */
trait RestClient

object RestClient extends Logger {

  def Call(url: String): String = {

      val httpClient: CloseableHttpClient = {
        val client = HttpClients.createDefault()
        client
      }
      val httpGet: HttpGet = new HttpGet(url);
      httpGet.addHeader("accept", "application/json")
      val response: CloseableHttpResponse = httpClient.execute(httpGet);
      val responseBody = EntityUtils.toString(response.getEntity);

      responseBody
  }
}
