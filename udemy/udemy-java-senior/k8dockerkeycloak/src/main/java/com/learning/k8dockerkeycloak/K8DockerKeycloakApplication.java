package com.learning.k8dockerkeycloak;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableEurekaClient
@ComponentScan(basePackages = {"com.learning.k8dockerkeycloak.controller"})
public class K8DockerKeycloakApplication {
    public static void main(String[] args) {
        SpringApplication.run(K8DockerKeycloakApplication.class, args);
    }

}
