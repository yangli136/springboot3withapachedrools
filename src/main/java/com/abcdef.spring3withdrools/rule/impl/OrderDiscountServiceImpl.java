package com.abcdef.springboot3withdrools.service.order.impl;

import java.util.UUID;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.slf4j.MDC;
import org.springframework.stereotype.Service;

import com.abcdef.springboot3withdrools.listener.AuditingAgendaEventListener;
import com.abcdef.springboot3withdrools.model.order.OrderDiscount;
import com.abcdef.springboot3withdrools.model.order.OrderRequest;
import com.abcdef.springboot3withdrools.service.order.OrderDiscountService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderDiscountServiceImpl implements OrderDiscountService {

    private final KieContainer kieContainer;
    private final AuditingAgendaEventListener listener;

    @Override
    public OrderDiscount getDiscount(OrderRequest orderRequest) {
        MDC.put("CORRELATION_ID", UUID.randomUUID().toString().replace("-", "").substring(0, 8));
        final KieSession kieSession = kieContainer.newKieSession();
        kieSession.addEventListener(listener);
        final OrderDiscount orderDiscount = new OrderDiscount();
        kieSession.setGlobal("orderDiscount", orderDiscount);
        kieSession.insert(orderRequest);
        kieSession.fireAllRules();
        kieSession.dispose();
        log.info("Final Result:{}", orderDiscount);
        return orderDiscount;
    }
}