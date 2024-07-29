package com.abcdef.springboot3withdrools.rule;

import org.drools.ruleunits.api.RuleUnitInstance;
import org.drools.ruleunits.dsl.SyntheticRuleUnit;

public interface SytheticRuleGenerator {

    RuleUnitInstance<SyntheticRuleUnit> loadRuleUin1tDefinition(SyntheticRuleUnit unit);

    SyntheticRuleUnit createSyntheticRuleUnit();

}
