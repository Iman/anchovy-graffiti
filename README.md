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
 
 
