package com.vlabs.hw.alpha.routes;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class ActiveMQRouter extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        from("timer:amq-timer?period=10000")
                .transform().constant("sending a fixed message")
                .log("sent ${body}")
                .to("activemq:test-queue-1");
    }
}
