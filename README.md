# Application Health Checker Service

Checks health of URL and saves the audit log in database asynchronously to Database.
App used Kafka to handle asynchronous messages and PostgreSQL as database

## Application does below steps to store the health information in PostgreSQL

1) Main application starts scheduler to check websites health and publish frequently
2) Main application starts Kafka Consumer to consumer and store the messages

NOTE : Producer and consumer can be run as separate service by making some configuration changes.

### Prerequisites to run the application

Configure timer, kafka , postgresql and other properties

### Configurations

Configurations can be changed in application.properties.
Handling secrets in the file is not in scope of this app. this should be handle in CICD strategy.

### Run application

App is built using Java19 & maven

* Run io.aiven.app.health.MainApplication class as java application from IDE
* or
* mvn compile exec:java -Dexec.mainClass=io.aiven.app.health.MainApplication

### Things could be done

* AVRO schema instead sending just status
* Unit test coverage
* Kafka client keystore and trust store location is full path not from class path

