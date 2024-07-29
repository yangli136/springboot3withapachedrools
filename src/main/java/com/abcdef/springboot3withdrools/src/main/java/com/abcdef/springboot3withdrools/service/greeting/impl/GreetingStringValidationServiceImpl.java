package com.abcdef.springboot3withdrools.service.greeting.impl;

import java.util.List;

import org.drools.ruleunits.api.RuleUnitInstance;
import org.drools.ruleunits.dsl.SyntheticRuleUnit;
import org.springframework.stereotype.Service;

import com.abcdef.springboot3withdrools.rule.SytheticRuleGenerator;
import com.abcdef.springboot3withdrools.service.greeting.GreetingStringValidationService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class GreetingStringValidationServiceImpl implements GreetingStringValidationService {
    private final SytheticRuleGenerator sytheticRuleGenerator;

    @SuppressWarnings("unchecked")
    @Override
    public List<String> validGreetingString(String greetingString, Integer length) {
        final SyntheticRuleUnit ruleUnit = this.sytheticRuleGenerator.createSyntheticRuleUnit();
        try (RuleUnitInstance<SyntheticRuleUnit> ruleUnitInstance = this.sytheticRuleGenerator
                .loadRuleUin1tDefinition(ruleUnit)) {

            ruleUnit.getGlobal("results", List.class).clear();
            ruleUnit.getDataStore("strings", String.class).add("Hello World");
            ruleUnitInstance.fire();
            List<String> results = ruleUnit.getGlobal("results", List.class);
            log.info("results1:{}", results);

            if (length != null) {
                ruleUnit.getGlobal("results", List.class).clear();
                ruleUnit.getDataStore("ints", Integer.class).add(length);
                ruleUnitInstance.fire();

                results = ruleUnit.getGlobal("results", List.class);
                log.info("results2:{}", results);
            }
            return results;
        }
    }
}
