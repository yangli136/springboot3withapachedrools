package com.abcdef.springboot3withdrools.service.loan.impl;

import java.util.UUID;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.slf4j.MDC;
import org.springframework.stereotype.Service;

import com.abcdef.springboot3withdrools.listener.AuditingAgendaEventListener;
import com.abcdef.springboot3withdrools.listener.ToStringAgendaEventListener;
import com.abcdef.springboot3withdrools.model.loan.LoanApplicant;
import com.abcdef.springboot3withdrools.model.loan.Rate;
import com.abcdef.springboot3withdrools.service.loan.LoanRateService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class LoanRateServiceImpl implements LoanRateService {

    private final KieContainer kieContainer;
    private final AuditingAgendaEventListener listener;
    private final ToStringAgendaEventListener toStringListener;

    @Override
    public Rate getRate(LoanApplicant loanApplicant) {
        // demo purpose
        MDC.put("CORRELATION_ID", UUID.randomUUID().toString().replace("-", "").substring(0, 8));
        final KieSession kieSession = kieContainer.newKieSession();
        kieSession.addEventListener(listener);
        kieSession.addEventListener(toStringListener);
        // kieSession.addEventListener(new org.drools.core.event.DebugProcessEventListener());
        // kieSession.addEventListener(new org.drools.core.event.DebugRuleRuntimeEventListener());
        final Rate rate = new Rate();
        kieSession.setGlobal("rate", rate);
        kieSession.insert(loanApplicant);
        kieSession.fireAllRules();
        kieSession.dispose();
        log.info("Final Result: {}", rate);
        return rate;
    }
}