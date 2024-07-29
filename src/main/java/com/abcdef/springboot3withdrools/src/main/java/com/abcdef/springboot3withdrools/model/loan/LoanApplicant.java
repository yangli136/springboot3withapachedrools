package com.abcdef.springboot3withdrools.model.loan;

import lombok.Data;

@Data
public class LoanApplicant {

    private String name;
    private int age;
    private int creditScore;
    private long annualSalary;
    private long existingDebt;
    private long loanAmount;
}