package com.grootan.scheduler;

import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.grootan.scheduler.clientBuilder.DynamoDbClient;
import com.grootan.scheduler.clientBuilder.EventbridgeClient;
import com.grootan.scheduler.dynamodbtable.RulesTableClass;
import com.grootan.scheduler.rules.PutRule;
import com.grootan.scheduler.rules.SetTarget;
import software.amazon.awssdk.services.eventbridge.EventBridgeClient;
import software.amazon.awssdk.services.eventbridge.model.PutRuleRequest;
import software.amazon.awssdk.services.eventbridge.model.PutTargetsRequest;

import java.io.IOException;

import java.util.HashMap;

import java.util.Map;

public class ScheduleFunction implements RequestHandler<Map<String, Object>, Map<String, Object>> {
  Map<String, String> requestParam = new HashMap<>();

  @Override
  public Map<String, Object> handleRequest(Map<String, Object> requestBody, Context context) {
    requestParam.put("p1", "hello");
    TriggerFunction.getPathVariable(requestBody, requestParam);
    System.out.println(requestBody);
    DynamoDbClient dynamoDbClientBuilder = new DynamoDbClient();
    DynamoDB dynamoDB = new DynamoDB(dynamoDbClientBuilder.getAmazonDynamoDbClient());
    EventBridgeClient eventBridgeClient = new EventbridgeClient().getEventBridgeClient();
    Table table = dynamoDB.getTable("Schedules");
    RulesTableClass rulesTableClass = new RulesTableClass();
    try {
      rulesTableClass.addItem(table, requestBody, dynamoDbClientBuilder);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    PutRuleRequest ruleRequest = PutRule.createRule(requestBody.get("Cron").toString());
    eventBridgeClient.putRule(ruleRequest);
    System.out.println(requestBody.get("RequestBody").toString());
    PutTargetsRequest targetsRequest =
        SetTarget.setTarget(requestBody.get("RequestBody").toString());
    eventBridgeClient.putTargets(targetsRequest);
    return requestBody;
  }
}
