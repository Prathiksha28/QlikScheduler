package com.grootan.scheduler.clientBuilder;

import software.amazon.awssdk.auth.credentials.EnvironmentVariableCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.eventbridge.EventBridgeClient;

public class EventbridgeClient {
    public EventBridgeClient getEventBridgeClient() {
        return EventBridgeClient
                .builder()
                .region(Region.AP_SOUTH_1)
                .credentialsProvider(EnvironmentVariableCredentialsProvider.create()).build();

    }
}
