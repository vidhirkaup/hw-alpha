package com.vlabs.hw.alpha.routes.flow.a;

import lombok.extern.slf4j.Slf4j;
import org.apache.camel.Body;
import org.apache.camel.ExchangeProperties;
import org.apache.camel.Headers;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Map;

@Slf4j
@Component
public class PlanA {

    @Value("#{${flowA.uris}}")
    private Map<String, String> uris;

    public String steps(@Headers Map<String, String> headers,
                        @ExchangeProperties Map<String, String> exchangeProperties,
                        @Body String body) {
        String step = headers.getOrDefault("step", "stepA");
        String uri = uris.get(step);
        return uri;
    }

}
