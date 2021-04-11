package com.vlabs.hw.alpha.routes;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class TimerRouter extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        try {
            from("timer:hw-timer-1")  // queue or listener
                    .to("log:hw-timer-log"); // database - Exchange[ExchangePattern: InOnly, BodyType: null, Body: [Body is null]]

            from("timer:hw-timer-2")
                    .transform().constant("a constant message")
                    .to("log:hw-timer-log");            // Exchange[ExchangePattern: InOnly, BodyType: String, Body: a constant message]

            from("timer:hw-timer-3")
                    .transform().constant(String.format("current time is {%s}", LocalDateTime.now()))
                    .to("log:hw-timer-log");            // Exchange[ExchangePattern: InOnly, BodyType: String, Body: current time is {2021-04-11T07:45:21.720}]
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception();
        }
    }
}
