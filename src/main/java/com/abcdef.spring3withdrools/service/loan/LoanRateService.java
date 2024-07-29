package com.abcdef.springboot3withdrools.service.loan;

import com.abcdef.springboot3withdrools.model.loan.LoanApplicant;
import com.abcdef.springboot3withdrools.model.loan.Rate;

public interface LoanRateService {

    Rate getRate(LoanApplicant loanApplicant);

}
