package com.vlabs.hw.alpha.routes.trials;

import lombok.extern.slf4j.Slf4j;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Slf4j
//@Component
public class KafkaRouter extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        from("file:files/kafka/json")
                .log("${body}")
                .to("kafka:json-1")
                .to("kafka:json-2")
                .to("kafka:json-3");

        from("file:files/kafka/xml")
                .log("${body}")
                .to("kafka:xml-1")
                .to("kafka:xml-2")
                .to("kafka:xml-3");
    }
}
