package com.vlabs.hw.alpha.routes;

import lombok.extern.slf4j.Slf4j;
import org.apache.camel.Body;
import org.apache.camel.ExchangeProperties;
import org.apache.camel.Headers;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

import java.util.Map;

@Slf4j
@Component
public class DynamicRouter extends RouteBuilder {
    @Override
    public void configure() throws Exception {

        from("direct:workflowOne-start")
                .bean("workflowOne", "start");
        from("direct:workflowOne-stepOne")
                .bean("workflowOne", "stepOne");
        from("direct:workflowOne-stepTwo")
                .bean("workflowOne", "stepTwo");
        from("direct:workflowOne-stepThree")
                .bean("workflowOne", "stepThree");
        from("direct:workflowOne-end")
                .bean("workflowOne", "end");

        from("timer:workflowOne?period=5000")
                .transform().constant("start workflow one")
                .dynamicRouter(method("workflowOne", "steps"));
    }
}

@Slf4j
@Component
class WorkflowOne {

    public String steps(@Headers Map<String, String> headers,
                        @ExchangeProperties Map<String, String> exchangeProperties,
                        @Body String body) {
        String step = (headers.get("step") == null ? "start" : headers.get("step"));
        switch (step) {
            case "start":
                step = "direct:workflowOne-start";
                break;
            case "direct:workflowOne-stepOne":
                step = "direct:workflowOne-stepOne";
                break;
            case "direct:workflowOne-stepTwo":
                step = "direct:workflowOne-stepTwo";
                break;
            case "direct:workflowOne-stepThree":
                step = "direct:workflowOne-stepThree";
                break;
            case "direct:workflowOne-end":
                step = "direct:workflowOne-end";
                break;
            case "end":
                step = null;
                break;
        }
        log.info("calling : {}", step);
        return step;
    }

    public String start(@Headers Map<String, String> headers,
                        @ExchangeProperties Map<String, String> exchangeProperties,
                        @Body String body) {
        log.info("---- [current step] {}", headers.get("step"));
        headers.put("step", "direct:workflowOne-stepOne");
        return "direct:workflowOne-stepOne";
    }

    public String stepOne(@Headers Map<String, String> headers,
                          @ExchangeProperties Map<String, String> exchangeProperties,
                          @Body String body) {
        log.info("---- [current step] {}", headers.get("step"));
        headers.put("step", "direct:workflowOne-stepTwo");
        return "direct:workflowOne-stepTwo";
    }

    public String stepTwo(@Headers Map<String, String> headers,
                          @ExchangeProperties Map<String, String> exchangeProperties,
                          @Body String body) {

        log.info("---- [current step] {}", headers.get("step"));
        headers.put("step", "direct:workflowOne-stepThree");
        return "direct:workflowOne-stepThree";
    }

    public String stepThree(@Headers Map<String, String> headers,
                            @ExchangeProperties Map<String, String> exchangeProperties,
                            @Body String body) {

        log.info("---- [current step] {}", headers.get("step"));
        headers.put("step", "direct:workflowOne-end");
        return "direct:workflowOne-end";
    }

    public String end(@Headers Map<String, String> headers,
                      @ExchangeProperties Map<String, String> exchangeProperties,
                      @Body String body) {
        log.info("---- [current step] {}", headers.get("step"));
        headers.put("step", "end");
        return null;
    }
}
