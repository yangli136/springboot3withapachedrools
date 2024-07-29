package com.abcdef.springboot3withdrools.model.loan;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Rate {

    private String loanStatus;
    private double loanRate;
}