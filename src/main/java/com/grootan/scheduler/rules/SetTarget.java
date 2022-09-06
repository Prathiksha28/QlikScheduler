package com.grootan.scheduler.rules;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.json.JSONObject;
import software.amazon.awssdk.services.eventbridge.model.PutTargetsRequest;
import software.amazon.awssdk.services.eventbridge.model.Target;

import java.util.Map;

public class SetTarget {
    public static PutTargetsRequest setTarget(String request) {
        System.out.println(request);
        JsonElement requestBody = JsonParser.parseString(request);
        System.out.println(requestBody);
        Target lambdaTarget = Target.builder()
                .arn("arn:aws:lambda:ap-south-1:650397611221:function:QlikScheduler-triggerFunction")
                .id("650397611221")
                .input(requestBody.toString())
                .build();
        return PutTargetsRequest.builder()
                .eventBusName("default")
                .rule("QlikScheduler")
                .targets(lambdaTarget)
                .build();

    }
}
