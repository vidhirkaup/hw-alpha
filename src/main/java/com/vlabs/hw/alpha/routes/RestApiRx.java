package com.vlabs.hw.alpha.routes;

import lombok.extern.slf4j.Slf4j;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
//@Component
public class RestApiRx extends RouteBuilder {

//    @Autowired
//    public Workflow workflow;
//
//    @Autowired
//    public CustomWorkflow customWorkflow;

    @Override
    public void configure() throws Exception {
        restConfiguration()
                .component("servlet")
                .host("localhost")
                .port("8081")
                .bindingMode(RestBindingMode.json);

        from("rest:get:http://localhost:8081/camel-api/alpha/hello")
                .transform().constant("Hello World");


//        rest("/workflow-one")
//                .get().route().to("bean:workflow?method=one");
//
//        rest("/workflow-two")
//                .get().route().to("bean:workflow?method=two");
//
//        rest("/workflow-three")
//                .get().route().to("bean:customWorkflow?method=three");

    }
}

@Slf4j
@Component
class Workflow {
    public void one() {
        log.info("> inside workflow.one()");
    }

    public void two() {
        log.info(">> inside workflow.two()");
    }
}

@Slf4j
@Component
class CustomWorkflow {

    public void three() {
        log.info(">>> inside workflow.three()");
    }
}