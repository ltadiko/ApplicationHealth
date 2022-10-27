<!-- GETTING STARTED -->
## Getting Started

# Application Health Checker Service

Checks health of URL and saves the audit log in database asynchronously to Database.
App uses Kafka to handle asynchronous messages and PostgreSQL to store


# DISCLAIMER: 
I kept the kafka & avro credentials to run the app. 
I will delete the instances and credentials from application.properties after review


## Application does below steps to store the health information in PostgreSQL

1) Main service starts scheduler to check websites health and publish frequently
2) Main service starts Kafka Consumer to consumer and store the messages

Producer and consumer can be run as separate service by making some configuration changes.

### Prerequisites to run the application

Configure timer, kafka , postgresql and other properties
Two Tables are created as mentioned in schema.sql.

### NOTE : 
Handling secrets from secret vault / location/ encryption in the application.properties is not in scope of this app.


### Installation

1. Install Java19 & Maven3.8.6
2. Clone the repo
   ```sh
   git clone https://github.com/aiven-recruitment/Java-20221021-ltadiko.git
   ```
3. mvn package
   ```sh
   * Run io.aiven.app.health.MainApplication class as java application from IDE
    or
   * mvn compile exec:java -Dexec.mainClass=io.aiven.app.health.MainApplication
      ```
4. update application.properties 


### Monitoring the logs
The website audit log information can be seen in WEBSITE_HEALTH_LOGS table.

### Things could be done 

* AVRO schema with error code and timestamp
* Unit test coverage
* Kafka client keystore and trust store location is full path of the file instead class path
* REST endpoints to monitor audit log
* Kafka connect to integrate in PostgresSQL


<!-- CONTACT -->
## Contact

Your Name - Lakshmaiah Tatikonda  - tlaxman88@gmail.com

Project Link: https://github.com/aiven-recruitment/Java-20221021-ltadiko.git] (https://github.com/ltadiko/)

<p align="right">(<a href="#readme-top">back to top</a>)</p>



<!-- ACKNOWLEDGMENTS -->
## Acknowledgments

Use this space to list resources you find helpful and would like to give credit to. I've included a few of my favorites to kick things off!

* [Aiven for Apache Kafka®](https://docs.aiven.io/docs/products/kafka.html)
* [PostgreSQL® welcome#](https://docs.aiven.io/docs/products/postgresql.html)

<p align="right">(<a href="#readme-top">back to top</a>)</p>
