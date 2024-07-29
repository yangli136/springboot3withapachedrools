package com.abcdef.springboot3withdrools.service.greeting;

import java.util.List;

public interface GreetingStringValidationService {

    List<String> validGreetingString(String greetingString, Integer length);
}
