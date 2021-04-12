package com.vlabs.hw.alpha.routes;

import lombok.extern.slf4j.Slf4j;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class RestApiRouter extends RouteBuilder {


    @Override
    public void configure() throws Exception {

        restConfiguration()
                .host("localhost")
                .port("8082");  // figure out how to set context-path

        from("timer:rest-api-timer?period=10000")
                .setHeader("from", () -> "EUR")
                .setHeader("to", () -> "INR")
                .to("rest:get:/camel-api/beta/convert/from/{from}/to/{to}")
                .log("${body}");
    }
}
