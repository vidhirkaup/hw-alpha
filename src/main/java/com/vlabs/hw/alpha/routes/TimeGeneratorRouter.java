package com.vlabs.hw.alpha.routes;

import lombok.extern.slf4j.Slf4j;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class TimeGeneratorRouter extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        from("direct:current-time")
                .to("ahc:http://localhost:8081//camel-api/alpha/ping")
                .end();
    }
}
