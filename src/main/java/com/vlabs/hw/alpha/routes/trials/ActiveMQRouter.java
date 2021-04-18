package com.vlabs.hw.alpha.routes.trials;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

//@Component
public class ActiveMQRouter extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        from("file:files/amq/json")
                .log("${body}")
                .multicast()
                .to("activemq:json-1", "activemq:json-2", "activemq:json-3");

        from("file:files/amq/xml")
                .log("${body}")
                .multicast()
                .to("activemq:xml-1", "activemq:xml-2", "activemq:xml-3");
    }
}
