package com.vlabs.hw.alpha.routes;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class ActiveMQRouter extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        from("file:files/json")
                .log("${body}")
                .to("activemq:test-queue-1")
                .to("activemq:test-queue-2")
                .to("activemq:test-queue-3");

    }
}
