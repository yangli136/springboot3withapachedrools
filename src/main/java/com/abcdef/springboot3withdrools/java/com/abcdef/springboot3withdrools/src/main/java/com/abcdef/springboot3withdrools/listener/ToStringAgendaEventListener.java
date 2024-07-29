package com.abcdef.springboot3withdrools.listener;

import org.kie.api.event.rule.AfterMatchFiredEvent;
import org.kie.api.event.rule.AgendaEventListener;
import org.kie.api.event.rule.BeforeMatchFiredEvent;
import org.kie.api.event.rule.MatchCancelledEvent;
import org.kie.api.event.rule.MatchCreatedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ToStringAgendaEventListener implements AgendaEventListener {

    protected static final transient Logger logger = LoggerFactory.getLogger(ToStringAgendaEventListener.class);

    public ToStringAgendaEventListener() {
        // intentionally left blank
    }

    public void matchCreated(MatchCreatedEvent event) {
        logger.debug(event.toString());
    }

    public void matchCancelled(MatchCancelledEvent event) {
        logger.debug(event.toString());
    }

    public void beforeMatchFired(BeforeMatchFiredEvent event) {
        logger.debug(event.toString());
    }

    public void afterMatchFired(AfterMatchFiredEvent event) {
        logger.debug(event.toString());
    }

    public void agendaGroupPopped(org.kie.api.event.rule.AgendaGroupPoppedEvent event) {
        logger.debug(event.toString());
    }

    public void agendaGroupPushed(org.kie.api.event.rule.AgendaGroupPushedEvent event) {
        logger.debug(event.toString());
    }

    public void beforeRuleFlowGroupActivated(org.kie.api.event.rule.RuleFlowGroupActivatedEvent event) {
        logger.debug(event.toString());
    }

    public void afterRuleFlowGroupActivated(org.kie.api.event.rule.RuleFlowGroupActivatedEvent event) {
        logger.debug(event.toString());
    }

    public void beforeRuleFlowGroupDeactivated(org.kie.api.event.rule.RuleFlowGroupDeactivatedEvent event) {
        logger.debug(event.toString());
    }

    public void afterRuleFlowGroupDeactivated(org.kie.api.event.rule.RuleFlowGroupDeactivatedEvent event) {
        logger.debug(event.toString());
    }
}
