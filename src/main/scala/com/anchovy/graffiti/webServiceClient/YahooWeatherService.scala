package com.anchovy.graffiti.webServiceClient

import com.google.gson.JsonObject
import org.mashupbots.socko.infrastructure.Logger

/**
 * Created by iman on 05/06/15.
 */
trait YahooWeatherService

private case class getCoordinates(latitude: Double, longitude: Double) extends YahooWeatherService

object YahooWeatherService extends Logger {

  @throws(classOf[Exception])
  def apiCall(coordinates: JsonObject): String = {

    if (coordinates.has("lat") && coordinates.has("lon")) {

      val url = (decorateEndpointUrl(getCoordinates(coordinates.get("lat").getAsDouble(), coordinates.get("lon").getAsDouble())))
      new RestClient().Call(url)

    } else {
      throw new NoSuchElementException("lat and lon are missing")
    }
  }

  private def decorateEndpointUrl(coordinates: getCoordinates): String =
    s"https://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20weather.forecast%20where%20woeid%20in%20(select%20woeid%20from%20geo.places.belongtos%20where%20member_woeid%20in%20(select%20woeid%20from%20geo.placefinder%20where%20text%3D%27${coordinates.latitude}%2C${coordinates.longitude}%27%20and%20gflags%3D%27R%27))%20and%20u%3D%27c%27%20and%20title%20not%20like%20%27%25error%27%20limit%201&format=json&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys&callback="

}
