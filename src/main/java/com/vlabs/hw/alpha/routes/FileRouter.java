package com.vlabs.hw.alpha.routes;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class FileRouter extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        from("file:files/input")
                .log("${body}")
                .choice()
                    .when(simple("${file:ext} == 'xml'"))
                    .log("XML File")
                    .when(simple("${file:ext} == 'json'"))
                    .log("JSON File")
                    .otherwise()
                    .log("not xml or json")
                .end()
                .to("file:files/output");

    }
}
