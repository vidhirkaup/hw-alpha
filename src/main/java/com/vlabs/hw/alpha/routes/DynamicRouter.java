package com.vlabs.hw.alpha.routes;

import lombok.extern.slf4j.Slf4j;
import org.apache.camel.Body;
import org.apache.camel.ExchangeProperties;
import org.apache.camel.Headers;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Map;

@Slf4j
//@Component
public class DynamicRouter extends RouteBuilder {

    @Value("${workflowOne.stepOne}")
    private String stepOne;

    @Value("${workflowOne.stepTwo}")
    private String stepTwo;

    @Value("${workflowOne.stepThree}")
    private String stepThree;

    @Override
    public void configure() throws Exception {

        from(stepOne)
                .bean("workflowOne", "stepOne");

        from(stepTwo)
                .bean("workflowOne", "stepTwo");

        from(stepThree)
                .bean("workflowOne", "stepThree");


        from("timer:workflowOne?period=5000")
                .transform().constant("start workflow one")
                .log("start: workflowOne")
                .dynamicRouter(method("workflowOne", "steps"))
                .log("end: workflowOne")
                .log("----------------");
    }
}

@Slf4j
@Component
class WorkflowOne {

    @Value("#{${workflowOne.uris}}")
    private Map<String, String> uris;

    public String steps(@Headers Map<String, String> headers,
                        @ExchangeProperties Map<String, String> exchangeProperties,
                        @Body String body) {
        String step = headers.getOrDefault("step", "stepOne");

        String uri = "end".equals(step) ? null : uris.get(step);

        log.info("calling {} -> {}", step, uri);
        return uri;
    }

    public void stepOne(@Headers Map<String, String> headers,
                        @ExchangeProperties Map<String, String> exchangeProperties,
                        @Body String body) {
        log.info("---- [ In step one. ]");
        headers.put("step", "stepTwo");
    }

    public void stepTwo(@Headers Map<String, String> headers,
                        @ExchangeProperties Map<String, String> exchangeProperties,
                        @Body String body) {
        log.info("---- [ In step two.. ] ");
        headers.put("step", "stepThree");
    }

    public void stepThree(@Headers Map<String, String> headers,
                          @ExchangeProperties Map<String, String> exchangeProperties,
                          @Body String body) {
        log.info("---- [ In step three... ] ");
        headers.put("step", "end");
    }
}
