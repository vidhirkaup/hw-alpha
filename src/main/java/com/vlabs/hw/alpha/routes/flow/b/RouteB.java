package com.vlabs.hw.alpha.routes.flow.b;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class RouteB extends RouteBuilder {

    @Value("${flowB.stepA}")
    private String stepA;

    @Value("${flowB.stepB}")
    private String stepB;

    @Value("${flowB.stepC}")
    private String stepC;

    @Value("${flowB.stepD}")
    private String stepD;

    @Override
    public void configure() throws Exception {
        from(stepA)
                .bean("flowBStepA", "perform");
        from(stepB)
                .bean("flowBStepB", "perform");
        from(stepC)
                .bean("flowBStepC", "perform");
        from(stepD)
                .bean("flowBStepD", "perform");

        from("direct:flowB")
                .log("start:: flow B")
                .dynamicRouter(method("planB", "steps"))
                .log("end:: flow B")
                .log("------------");
    }
}
