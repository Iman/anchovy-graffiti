#Anchovy Graffiti 
 
Anchovy Graffiti is a showcase application developed using Scala and Socko framework interfacing Yahoo weather RESTful API and responding the result as json in real-time over WebSocket.

Service | Status | Description
------- | ------ | -----------
Travis CI | [![Build Status](https://travis-ci.org/Iman/anchovy-graffiti.svg)](https://travis-ci.org/Iman/anchovy-graffiti) | Continuous Integration and Test Automation
Codacy |  [![Codacy Badge](https://www.codacy.com/project/badge/6bf3c4400e5e4cefb9b131c74c7f4b09)](https://www.codacy.com/app/iman/anchovy-graffiti)| Code Quality
Bintray | [![Download](https://api.bintray.com/packages/iman/generic/anchovy-graffiti/images/download.svg) ](https://bintray.com/iman/generic/anchovy-graffiti/_latestVersion) | Latest Version on Bintray

##Request Example 
 
 [ws://localhost:8080](ws://localhost:8080)
 
```json
 {
 "lat": 51.5072,
 "lon":-0.127758
 }
 ```

##Running Test
Autobahn websocket test:
```
sh AutobahnTest.sh
```
 
Scalatest:
```
sbt test
``` 
 
 ```
 [info] WebSocketHandlerTest:
 [info] - Web Socket Handler
 [info] RestClientTest:
 [info] - Rest client mock test
 [info] - Get URL with ClientProtocolException
 [info] - Test get URL
 [info] YahooWeatherServiceTest:
 [info] - Yahoo Weather Service Client
 [info] - Pass wrong json object
 [info] - Pass correct json object
 [info] Run completed in 1 second, 699 milliseconds.
 [info] Total number of tests run: 7
 [info] Suites: completed 3, aborted 0
 [info] Tests: succeeded 7, failed 0, canceled 0, ignored 0, pending 0
 [info] All tests passed.
 [success] Total time: 13 s, completed 27-Jul-2015 00:29:16
 ```
 
