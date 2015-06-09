package com.anchovy.graffiti.WebServiceClient

import com.anchovy.graffiti.webServiceClient.{getCoordinates, YahooWeatherService}
import com.google.gson.{JsonElement, JsonParser}
import org.mashupbots.socko.infrastructure.Logger
import org.mockito.Mockito._
import org.scalatest.{BeforeAndAfter, FunSuite}
import org.scalatest.mock.MockitoSugar

/**
 * Created by iman on 08/06/15.
 */
class YahooWeatherServiceTest extends FunSuite with BeforeAndAfter with MockitoSugar with Logger {

  test("Yahoo Weather Service Client") {

    val getCoordinatesMock = mock[getCoordinates]
    val coordinates = getCoordinates.apply(51.5072, -0.127758);
    when(getCoordinatesMock.copy(coordinates.latitude, coordinates.longitude)).thenCallRealMethod()
    assert(getCoordinatesMock.copy(coordinates.latitude, coordinates.longitude) == coordinates)

    trait MockYahooWeatherService {

      val _dummyJsonRequest = "{\"lat\": 51.5072, \"lon\":-0.127758}"
      val _dummyJsonResponse = "{\"query\":{\"count\":1,\"created\":\"2015-06-08T13:35:52Z\",\"lang\":\"en-US\",\"results\":{\"channel\":{\"title\":\"Yahoo! Weather - London, GB\",\"link\":\"http://us.rd.yahoo.com/dailynews/rss/weather/London__GB/*http://weather.yahoo.com/forecast/UKXX0085_c.html\",\"description\":\"Yahoo! Weather for London, GB\",\"language\":\"en-us\",\"lastBuildDate\":\"Mon, 08 Jun 2015 1:48 pm BST\",\"ttl\":\"60\",\"location\":{\"city\":\"London\",\"country\":\"United Kingdom\",\"region\":\"\"},\"units\":{\"distance\":\"km\",\"pressure\":\"mb\",\"speed\":\"km/h\",\"temperature\":\"C\"},\"wind\":{\"chill\":\"17\",\"direction\":\"60\",\"speed\":\"19.31\"},\"atmosphere\":{\"humidity\":\"42\",\"pressure\":\"1015.92\",\"rising\":\"0\",\"visibility\":\"9.99\"},\"astronomy\":{\"sunrise\":\"4:44 am\",\"sunset\":\"9:12 pm\"},\"image\":{\"title\":\"Yahoo! Weather\",\"width\":\"142\",\"height\":\"18\",\"link\":\"http://weather.yahoo.com\",\"url\":\"http://l.yimg.com/a/i/brand/purplelogo//uh/us/news-wea.gif\"},\"item\":{\"title\":\"Conditions for London, GB at 1:48 pm BST\",\"lat\":\"51.5\",\"long\":\"-0.13\",\"link\":\"http://us.rd.yahoo.com/dailynews/rss/weather/London__GB/*http://weather.yahoo.com/forecast/UKXX0085_c.html\",\"pubDate\":\"Mon, 08 Jun 2015 1:48 pm BST\",\"condition\":{\"code\":\"34\",\"date\":\"Mon, 08 Jun 2015 1:48 pm BST\",\"temp\":\"17\",\"text\":\"Fair\"},\"description\":\"\\n<img src=\\\"http://l.yimg.com/a/i/us/we/52/34.gif\\\"/><br />\\n<b>Current Conditions:</b><br />\\nFair, 17 C<BR />\\n<BR /><b>Forecast:</b><BR />\\nMon - Cloudy. High: 18 Low: 8<br />\\nTue - Partly Cloudy. High: 17 Low: 8<br />\\nWed - AM Clouds/PM Sun. High: 21 Low: 11<br />\\nThu - Partly Cloudy. High: 23 Low: 14<br />\\nFri - PM Showers. High: 24 Low: 13<br />\\n<br />\\n<a href=\\\"http://us.rd.yahoo.com/dailynews/rss/weather/London__GB/*http://weather.yahoo.com/forecast/UKXX0085_c.html\\\">Full Forecast at Yahoo! Weather</a><BR/><BR/>\\n(provided by <a href=\\\"http://www.weather.com\\\" >The Weather Channel</a>)<br/>\\n\",\"forecast\":[{\"code\":\"26\",\"date\":\"8 Jun 2015\",\"day\":\"Mon\",\"high\":\"18\",\"low\":\"8\",\"text\":\"Cloudy\"},{\"code\":\"30\",\"date\":\"9 Jun 2015\",\"day\":\"Tue\",\"high\":\"17\",\"low\":\"8\",\"text\":\"Partly Cloudy\"},{\"code\":\"30\",\"date\":\"10 Jun 2015\",\"day\":\"Wed\",\"high\":\"21\",\"low\":\"11\",\"text\":\"AM Clouds/PM Sun\"},{\"code\":\"30\",\"date\":\"11 Jun 2015\",\"day\":\"Thu\",\"high\":\"23\",\"low\":\"14\",\"text\":\"Partly Cloudy\"},{\"code\":\"39\",\"date\":\"12 Jun 2015\",\"day\":\"Fri\",\"high\":\"24\",\"low\":\"13\",\"text\":\"PM Showers\"}],\"guid\":{\"isPermaLink\":\"false\",\"content\":\"UKXX0085_2015_06_12_7_00_BST\"}}}}}}"
      val jsonElement: JsonElement = new JsonParser().parse(_dummyJsonRequest)
      val dummyJsonRequest = jsonElement.getAsJsonObject()
      val yahooWeatherService = mock[YahooWeatherService.type]

      when(yahooWeatherService.apiCall(dummyJsonRequest)).thenReturn(_dummyJsonResponse)
      val yahooWeatherServiceResult = yahooWeatherService.apiCall(dummyJsonRequest)

      assert(yahooWeatherServiceResult == dummyJsonRequest)
      assert(_dummyJsonResponse == yahooWeatherService.apiCall(dummyJsonRequest))
      assert(!yahooWeatherServiceResult.isEmpty)

      yahooWeatherService.apiCall(dummyJsonRequest)
      val responseJsonElement: JsonElement = new JsonParser().parse(yahooWeatherService.apiCall(dummyJsonRequest))
      assert(responseJsonElement.getAsJsonObject().isJsonObject())

      //decorateEndpointUrl
      val url = "https://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20weather.forecast%20where%20woeid%20in%20(select%20woeid%20from%20geo.places.belongtos%20where%20member_woeid%20in%20(select%20woeid%20from%20geo.placefinder%20where%20text%3D%2751.507351%2C-0.127758%27%20and%20gflags%3D%27R%27))%20and%20u%3D%27c%27%20and%20title%20not%20like%20%27%25error%27%20limit%201&format=json&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys&callback="

      when(yahooWeatherService.decorateEndpointUrl(coordinates)).thenReturn(url)
      val endPoint = yahooWeatherService.decorateEndpointUrl(coordinates)

      assert(url == endPoint)

    }

  }
}
