package com.vlabs.hw.alpha.controller;

import org.apache.camel.ProducerTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HWController {

    @Autowired
    private ProducerTemplate producerTemplate;

    @GetMapping("/hello/{msg}")
    public String hello(@PathVariable("msg") String msg) {
        String res = producerTemplate.requestBody("direct:current-time", null, String.class);
        return String.format("hello %s... %s.", msg, res);
    }
}
