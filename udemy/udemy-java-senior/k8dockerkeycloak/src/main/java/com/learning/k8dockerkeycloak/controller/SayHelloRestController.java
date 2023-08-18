package com.learning.k8dockerkeycloak.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class SayHelloRestController {
    @Autowired
    private static final Logger logger = LoggerFactory.getLogger(SayHelloRestController.class);
    @Value("${message}")
    private String message;

    @GetMapping(value = "/api/greeting")

    public String sayHello(Authentication auth) {
        return "Hello:" + auth.getName() + ", welcome to the course!" + this.message;

    }
}
