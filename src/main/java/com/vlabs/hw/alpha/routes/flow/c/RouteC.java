package com.vlabs.hw.alpha.routes.flow.c;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class RouteC extends RouteBuilder {

    @Value("${flowC.stepA}")
    private String stepA;

    @Value("${flowC.stepB}")
    private String stepB;

    @Value("${flowC.stepC}")
    private String stepC;

    @Value("${flowC.stepD}")
    private String stepD;

    @Value("${flowC.stepE}")
    private String stepE;

    @Value("${flowC.stepF}")
    private String stepF;

    @Override
    public void configure() throws Exception {
        from(stepA)
                .bean("flowCStepA", "perform");
        from(stepB)
                .bean("flowCStepB", "perform");
        from(stepC)
                .bean("flowCStepC", "perform");
        from(stepD)
                .bean("flowCStepD", "perform");
        from(stepE)
                .bean("flowCStepE", "perform");
        from(stepF)
                .bean("flowCStepF", "perform");

        from("direct:flowC")
                .log("start:: flow C")
                .dynamicRouter(method("planC", "steps"))
                .log("end:: flow C")
                .log("------------");
    }
}
