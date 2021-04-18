package com.vlabs.hw.alpha.controller;

import org.apache.camel.ProducerTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
public class FlowController {

    @Autowired
    private ProducerTemplate producerTemplate;

    @GetMapping("/start/{flow}")
    public String start(@PathVariable("flow") String flow){
        producerTemplate.requestBody("direct:" + flow, null, String.class);
        return String.format("started %s @ %s",
                flow,
                LocalDateTime.now());
    }
}
