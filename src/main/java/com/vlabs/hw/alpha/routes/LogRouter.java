package com.vlabs.hw.alpha.routes;

import lombok.extern.slf4j.Slf4j;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Slf4j
//@Component
public class LogRouter extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        from("direct:log-file-values")
                .log("${messageHistory} [${file:absolute.path}]")
                .log("[${file:name}] [${file:name.ext}] [${file:name.noext}] [${file:onlyname}]")
                .log("[${file:onlyname.noext}] [${file:parent}] [${file:path}] [${file:absolute}]")
                .log("[${file:size}] [${file:modified}]")
                .log("[${routeId} ${camelId} ${body}]");
    }
}
