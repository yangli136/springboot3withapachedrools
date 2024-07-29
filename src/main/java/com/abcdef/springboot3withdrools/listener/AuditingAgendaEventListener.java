package com.abcdef.springboot3withdrools.listener;

import java.util.Map;

import org.drools.core.event.DefaultAgendaEventListener;
import org.kie.api.definition.rule.Rule;
import org.kie.api.event.rule.AfterMatchFiredEvent;
import org.kie.api.runtime.rule.Match;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AuditingAgendaEventListener extends DefaultAgendaEventListener {

    @Override
    public void afterMatchFired(AfterMatchFiredEvent event) {
        final Match match = event.getMatch();
        final StringBuilder sb = new StringBuilder("\nObjects:").append(match.getObjects());

        final Rule rule = event.getMatch().getRule();
        sb.append("\nRule fired: ").append(rule.getName());
        retrieveMetaDataMap(rule, sb);

        log.info(sb.toString());
    }

    private void retrieveMetaDataMap(Rule rule, final StringBuilder sb) {
        final Map<String, Object> ruleMetaDataMap = rule.getMetaData();
        if (ruleMetaDataMap.size() > 0) {
            sb.append("\n  With [" + ruleMetaDataMap.size() + "] meta-data:");
            for (String key : ruleMetaDataMap.keySet()) {
                sb.append("\n    key=" + key + ", value=" + ruleMetaDataMap.get(key));
            }
        }
    }

}