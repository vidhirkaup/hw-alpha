package com.vlabs.hw.alpha.routes.flow.a;

import lombok.extern.slf4j.Slf4j;
import org.apache.camel.Body;
import org.apache.camel.ExchangeProperties;
import org.apache.camel.Headers;
import org.springframework.stereotype.Component;

import java.util.Map;

@Slf4j
@Component
public class StepC {
    public void perform(@Headers Map<String, String> headers,
                        @ExchangeProperties Map<String, String> exchangeProperties,
                        @Body String body) {
          log.info("---- [performing Step C]");
          headers.put("step", "end");
    }
}
