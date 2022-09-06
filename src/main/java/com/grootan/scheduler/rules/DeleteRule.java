package com.grootan.scheduler.rules;

import software.amazon.awssdk.services.eventbridge.EventBridgeClient;
import software.amazon.awssdk.services.eventbridge.model.DeleteRuleRequest;
import software.amazon.awssdk.services.eventbridge.model.DisableRuleRequest;

public class DeleteRule {
    public void deleteRule(String ruleName, EventBridgeClient eventBridgeClient) {
        DisableRuleRequest disableRuleRequest = DisableRuleRequest.builder()
                .name(ruleName)
                .eventBusName("default")
                .build();
        eventBridgeClient.disableRule(disableRuleRequest);
        DeleteRuleRequest ruleRequest = DeleteRuleRequest.builder()
                .name(ruleName)
                .eventBusName("default")
                .build();
        eventBridgeClient.deleteRule(ruleRequest);
    }
}
