package com.vlabs.hw.alpha.routes.flow.c;

import lombok.extern.slf4j.Slf4j;
import org.apache.camel.Body;
import org.apache.camel.ExchangeProperties;
import org.apache.camel.Headers;
import org.springframework.stereotype.Component;

import java.util.Map;

@Slf4j
@Component
public class FlowCStepF {
    public void perform(@Headers Map<String, String> headers,
                        @ExchangeProperties Map<String, String> exchangeProperties,
                        @Body String body) {
          log.info("---- [performing Step F]");
          headers.put("step", "end");
    }
}
