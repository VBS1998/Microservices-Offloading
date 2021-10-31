package com.gustavovbs.microservicesoffloading;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.gustavovbs")
public class MicroservicesOffloadingApplication {

    public static void main(String[] args) {
        SpringApplication.run(MicroservicesOffloadingApplication.class, args);
    }

}
