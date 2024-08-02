package com.abcdef.springboot3withdrools.ruleunit.order;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.drools.core.event.DebugRuleRuntimeEventListener;
import org.drools.ruleunits.api.RuleUnitInstance;
import org.drools.ruleunits.api.RuleUnitProvider;
import org.drools.ruleunits.api.conf.RuleConfig;
import org.junit.jupiter.api.Test;
import org.kie.api.runtime.rule.QueryResults;
import org.slf4j.MDC;

import com.abcdef.springboot3withdrools.listener.AuditingAgendaEventListener;
import com.abcdef.springboot3withdrools.listener.ToStringAgendaEventListener;
import com.abcdef.springboot3withdrools.model.order.CustomerType;
import com.abcdef.springboot3withdrools.model.order.OrderRequest;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class OrderUnitTest {

    @Test
    public void isHelloWorld() {
        MDC.put("CORRELATION_ID", UUID.randomUUID().toString().replace("-", "").substring(0, 8));
        OrderUnit unit = new OrderUnit();
        OrderRequest orderRequest = new OrderRequest();
        orderRequest.setCustomerType(CustomerType.LOYAL);
        orderRequest.setAge(8);
        orderRequest.setAmount(50000);
        orderRequest.setCustomerNumber("1111");
        unit.getOrderRequest().add(orderRequest);
        final RuleConfig ruleConfig = RuleUnitProvider.get().newRuleConfig();
        ruleConfig.getAgendaEventListeners().add(new AuditingAgendaEventListener());
        ruleConfig.getAgendaEventListeners().add(new ToStringAgendaEventListener());
        ruleConfig.getRuleRuntimeListeners().add(new DebugRuleRuntimeEventListener());

        // Files end with drl.xls and drl.xlsx will be automatically loaded from package the same as RuleUnit.
        // Apache POI is used to load Excel file, POI-HSSF supports Excel '97(-2007) file format.
        // PON-XSSF/SXSSF supports Excel 2007 OOXML (.xlsx).
        try (RuleUnitInstance<OrderUnit> unitInstance = RuleUnitProvider.get().createRuleUnitInstance(unit,
                ruleConfig)) {
            assertThat(unitInstance.fire()).isEqualTo(2);
            log.info("results:{}", unit.getOrderDiscount());
            assertThat(unit.getOrderDiscount().getDiscount()).isEqualTo(15);
            final QueryResults queryResults = unitInstance.executeQuery("getDiscount");
            List<Map<String, Object>> queryResultList = queryResults.toList();
            log.info("queryResultList:{}", queryResultList);
            assertThat(queryResultList.get(0).get("$amount")).isEqualTo(50000);
        }
    }
}
