package com.abcdef.springboot3withdrools;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.abcdef.springboot3withdrools")
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
// https://medium.com/@reachansari/spring-boot-drools-decision-table-example-fd569eec56e0
// https://medium.com/@tobintom/spring-boot-with-drools-rules-engine-d73e1af3c411