package com.abcdef.springboot3withdrools.model.greeting;

import lombok.Data;

@Data
public class GreetingRequest {

    private String greetingString;
    private Integer length;
}
