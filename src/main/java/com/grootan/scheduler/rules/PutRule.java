package com.grootan.scheduler.rules;

import software.amazon.awssdk.services.eventbridge.model.PutRuleRequest;

public class PutRule {
    public static PutRuleRequest createRule(String cron) {
        return PutRuleRequest.builder()
                .name("QlikScheduler")
                .eventBusName("default")
                .scheduleExpression(cron)
                .state("ENABLED")
                .build();
    }
}
