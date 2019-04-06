# BigPanda Back-end Task

# Author: Tamir Mayblat

## Implement a Non Blocking Producer/Consumer stream processing service that exposes an HTTP api.
Using a given blackbox executable which generates infinite stream of lines of event data encoded in JSON. 

## How to build:
Edit "generator_path" property in application.properties file, update it with path to generator (by default it's c:\").

Run `mvn clean install`

## How to run:
Go to the project's target directory and run this command:
`java -jar application-0.0.1-SNAPSHOT.jar`

## Using the controllers: 
* /test - used to check that the application is alive, http://localhost:8080/hello 
* /types - Retrieves Event types counters, http://localhost:8080/types
* /words - Retrieves word counters, http://localhost:8080/words

## This application uses log4j to log the events and errors of the application. 
* The default mode is debug
* Log file is saved in C:\\log4j-application.log
* Edit the log log4j.properties file to change the log configuration.

## TODO's:
* Add reactive frameworks (RXJava or akka) to improve this implementation.   
* HTML based client with UI which would support filtering and sorted output
* Unit testing and validations for the input generator's file location and output streams
 
