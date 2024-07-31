package com.abcdef.springboot3withdrools.ruleunit.greeting;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.UUID;

import org.drools.ruleunits.api.RuleUnitInstance;
import org.drools.ruleunits.api.RuleUnitProvider;
import org.drools.ruleunits.api.conf.RuleConfig;
import org.junit.jupiter.api.Test;
import org.slf4j.MDC;

import com.abcdef.springboot3withdrools.listener.AuditingAgendaEventListener;
import com.abcdef.springboot3withdrools.listener.ToStringAgendaEventListener;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GreetingUnitProviderImplTest {

    @Test
    public void isHelloWorld() {
        MDC.put("CORRELATION_ID", UUID.randomUUID().toString().replace("-", "").substring(0, 8));
        GreetingUnit unit = new GreetingUnit();
        unit.getStrings().add("Hello World");

        final RuleConfig ruleConfig = RuleUnitProvider.get().newRuleConfig();
        ruleConfig.getAgendaEventListeners().add(new AuditingAgendaEventListener());
        ruleConfig.getAgendaEventListeners().add(new ToStringAgendaEventListener());
        try (RuleUnitInstance<GreetingUnit> unitInstance = RuleUnitProvider.get().createRuleUnitInstance(unit,
                ruleConfig)) {
            assertThat(unitInstance.fire()).isEqualTo(2);
            log.info("results:{}", unit.getResults());
            assertThat(unit.getResults()).contains("it worked!");
            assertThat(unit.getResults()).contains("it also worked with HELLO WORLD");
            assertThat(unit.getResults()).doesNotContain("this shouldn't fire");
            assertThat(unit.getResults()).doesNotContain("String 'Hello World' is 11 characters long");
        }
    }

    @Test
    public void lengthGreaterThan5() {
        MDC.put("CORRELATION_ID", UUID.randomUUID().toString().replace("-", "").substring(0, 8));
        GreetingUnit unit = new GreetingUnit();
        unit.getStrings().add("Hello World");
        unit.getInts().add(11);

        final RuleConfig ruleConfig = RuleUnitProvider.get().newRuleConfig();
        ruleConfig.getAgendaEventListeners().add(new AuditingAgendaEventListener());
        ruleConfig.getAgendaEventListeners().add(new ToStringAgendaEventListener());
        try (RuleUnitInstance<GreetingUnit> unitInstance = RuleUnitProvider.get().createRuleUnitInstance(unit,
                ruleConfig)) {
            assertThat(unitInstance.fire()).isEqualTo(3);
            log.info("results:{}", unit.getResults());
            assertThat(unit.getResults()).contains("it worked!");
            assertThat(unit.getResults()).contains("it also worked with HELLO WORLD");
            assertThat(unit.getResults()).doesNotContain("this shouldn't fire");
            assertThat(unit.getResults()).contains("String 'Hello World' is 11 characters long");
        }
    }
}