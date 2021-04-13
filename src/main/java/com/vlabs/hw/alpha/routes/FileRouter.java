package com.vlabs.hw.alpha.routes;

import lombok.extern.slf4j.Slf4j;
import org.apache.camel.Body;
import org.apache.camel.ExchangeProperties;
import org.apache.camel.Headers;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Random;

@Component
public class FileRouter extends RouteBuilder {

    @Autowired
    Dice dice;

    @Override
    public void configure() throws Exception {
        from("file:files/input")
                .log("${body}")
                .choice()
                    .when(method(dice, "spin"))
                        .log("first time: > 0.5")

                    .when(method(dice, "spin"))
                        .log("second time: > 0.5")

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

@Slf4j
@Component
class Dice {
    public boolean spin(@Headers Map<String, String> headers,
                                   @ExchangeProperties Map<String, String> exchangeProperties,
                                   @Body String body){
        log.info("headers: {}", headers);
        log.info("exchangeProperties: {}", exchangeProperties);
        log.info("body: {}", body);

        double val = new Random().nextDouble();
        log.info(String.valueOf(val));
        return (val  > 0.5 ? true : false);
    }
}