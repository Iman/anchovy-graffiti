#Anchovy Graffiti 
 
Anchovy Graffiti is a showcase application developed using Scala and Socko framework interfacing Yahoo weather RESTful API and responding the result as json in WebSocket.

Travis CI
[![Build Status](https://travis-ci.org/Iman/anchovy-graffiti.svg)](https://travis-ci.org/Iman/anchovy-graffiti)

Codacy - Automated Code Review
[![Codacy Badge](https://www.codacy.com/project/badge/6bf3c4400e5e4cefb9b131c74c7f4b09)](https://www.codacy.com/app/iman/anchovy-graffiti)

##Request Example 
 
 [ws://localhost:8888](ws://localhost:8888)
 
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
 
 