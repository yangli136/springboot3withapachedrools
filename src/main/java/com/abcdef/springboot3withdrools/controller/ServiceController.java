package com.abcdef.springboot3withdrools.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.abcdef.springboot3withdrools.model.greeting.GreetingRequest;
import com.abcdef.springboot3withdrools.model.loan.LoanApplicant;
import com.abcdef.springboot3withdrools.model.loan.Rate;
import com.abcdef.springboot3withdrools.model.order.OrderDiscount;
import com.abcdef.springboot3withdrools.model.order.OrderRequest;
import com.abcdef.springboot3withdrools.service.greeting.GreetingStringValidationService;
import com.abcdef.springboot3withdrools.service.loan.LoanRateService;
import com.abcdef.springboot3withdrools.service.order.OrderDiscountService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@RequestMapping("/service")
@Slf4j
public class ServiceController {

    private final LoanRateService loanRateService;
    private final OrderDiscountService orderDiscountService;
    private final GreetingStringValidationService greetingStringValidationService;

    /*
     * { "name": "TestLoanApplication1", "age": 30, "creditScore": 760, "annualSalary": 1000000, "existingDebt": 600000, "loanAmount": 300000 }
     */
    @PostMapping("/get-rate")
    public ResponseEntity<Rate> getRate(@RequestBody LoanApplicant request) {
        final Rate rate = loanRateService.getRate(request);
        return new ResponseEntity<>(rate, HttpStatus.OK);
    }

    /*
     * { "customerNumber": "1111", "age": 8, "amount": 50000, "customerType": "LOYAL" }
     */
    @PostMapping("/get-discount")
    public ResponseEntity<OrderDiscount> getDiscount(@RequestBody OrderRequest orderRequest) {
        final OrderDiscount discount = orderDiscountService.getDiscount(orderRequest);
        return new ResponseEntity<>(discount, HttpStatus.OK);
    }

    /*
     * { "greetingString": "Hello World", "length": 11 }
     */
    @PostMapping("/validate-greeting")
    public ResponseEntity<List<String>> validateGreeting(@RequestBody GreetingRequest request) {
        log.info("request:{}", request);
        final List<String> results = greetingStringValidationService.validGreetingString(request.getGreetingString(),
                request.getLength());
        return new ResponseEntity<>(results, HttpStatus.OK);
    }
}
