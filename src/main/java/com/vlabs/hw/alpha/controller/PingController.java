package com.vlabs.hw.alpha.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
public class PingController {

    @GetMapping("/ping")
    public String ping(){
        return String.format("hw-alpha service pinged @ [%s]", LocalDateTime.now());
    }
}
