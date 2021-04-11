package com.vlabs.hw.alpha.routes;

import lombok.extern.slf4j.Slf4j;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class TimerRouter extends RouteBuilder {

    @Autowired
    private DummyTimer dummyTimer;

    @Autowired
    private DummyLogger dummyLogger;

    @Override
    public void configure() throws Exception {
        try {
            from("timer:hw-timer-3")
                    .log("> raw msg: ${body}")

                    .transform().constant("modified the body here")
                    .log("> transform step - constant: ${body}")

                    .bean(dummyTimer, "getFormattedCurrentTime")
                    .log("> transform step - in bean: ${body}")

                    .process(new DummyProcessor())
                    .log("> processing step - in bean: ${body}")

                    .bean(dummyLogger)
                    .log("> processing step - in bean: ${body}")

                    .to("log:hw-timer-log");
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception();
        }
    }
}

@Component
class DummyTimer {
    public String getCurrentTime() {
        return String.format("current time is [%s]", LocalDateTime.now());
    }

    public String getFormattedCurrentTime() {
        return String.format("current time is [%tR]", LocalDateTime.now());
    }
}

@Slf4j
@Component
class DummyLogger {
    public void simplyLog(String msg) {
        log.info("- simple processor: {}", msg.toUpperCase());
    }
}

@Slf4j
class DummyProcessor implements Processor {

    @Override
    public void process(Exchange exchange) throws Exception {
        log.info("- camel processor: {}", exchange.getMessage().getBody());
    }
}