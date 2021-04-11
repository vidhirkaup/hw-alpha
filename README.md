# Getting Started

### Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.4.4/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/2.4.4/maven-plugin/reference/html/#build-image)
* [Spring Web](https://docs.spring.io/spring-boot/docs/2.4.4/reference/htmlsingle/#boot-features-developing-web-applications)
* [Spring Boot DevTools](https://docs.spring.io/spring-boot/docs/2.4.4/reference/htmlsingle/#using-boot-devtools)

### Guides
The following guides illustrate how to use some features concretely:

* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/bookmarks/)
* [Using Apache Camel with Spring Boot](https://camel.apache.org/camel-spring-boot/latest/spring-boot.html)


### Local Setup
* For AMQ
  * Docker container: docker run -p 61616:61616 -p 8161:8161 rmohr/activemq
    * console - http://localhost:8161/admin/queues.jsp
  * Test using [cp 1000.xml ../../files/amq/xml/; cp 1000.json ../../files/amq/json/]
  
* For Kafka
  * Docker container: ~\source\java\hw-alpha\help> docker-compose up
    * This needs the docker-compose.yaml file
  * For this issue {java.net.UnknownHostException: 02a85aa7****}
    * Refer https://www.howtogeek.com/howto/27350/beginner-geek-how-to-edit-your-hosts-file/
  * Test using [cp 1000.xml ../../files/kafka/xml/; cp 1000.json ../../files/kafka/json/]

