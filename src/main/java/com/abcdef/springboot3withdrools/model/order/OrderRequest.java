package com.abcdef.springboot3withdrools.model.order;

import lombok.Data;

@Data
public class OrderRequest {

    private String customerNumber;
    private Integer age;
    private Integer amount;
    private CustomerType customerType;
}