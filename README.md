# Application Health Checker: Log and Monitor the Health Status of website

## Application does below steps to store the health information in PostgreSQL

1) Main application starts timer service to check health status and publish status to Kafka
2) Main application starts Kafka Consumer  
3) Consume the messages from Kafka & stores in PostgreSQL

### Run application

* Run io.aiven.app.health.MainApplication class as java application from IDE

### Prerequisites to run the application

Configure timer details
Configure Database and Kafka network and credentials in application properties

### Things could be done
AVRO schema instead sending just status
