package com.grootan.scheduler.dynamodbtable;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.dynamodbv2.document.*;
import com.amazonaws.services.dynamodbv2.document.spec.GetItemSpec;
import com.amazonaws.services.dynamodbv2.document.spec.QuerySpec;
import com.amazonaws.services.dynamodbv2.document.utils.ValueMap;
import com.amazonaws.services.dynamodbv2.model.*;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.grootan.scheduler.clientBuilder.DynamoDbClient;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class RulesTableClass {
  public void addItem(Table table, Map<String, Object> request, DynamoDbClient dynamoDB)
      throws IOException {
    //        System.out.println(request);

    /*
    JSONObject requestBody = new JSONObject(request);
    System.out.println(requestBody);
    Map<String, AttributeValue> item = new HashMap<>();
    AttributeValue username = new AttributeValue((String) requestBody.get("username"));
    item.put("UserName",username);
    AttributeValue scheduleName=new AttributeValue((String) requestBody.get("scheduleName"));
    item.put("ScheduleName",scheduleName);
    AttributeValue requestUrl = new AttributeValue((String) requestBody.get("requestUrl"));
    item.put("requestUrl",requestUrl);
    */

    //        JSONObject userRequestBody = new JSONObject(requestBody.getJSONObject("requestBody"));
    //        Map<String,AttributeValue> req= new HashMap<>();
    //        for(int i=0;i<userRequestBody.length();i++)
    //        {
    //            req.put("param"+(i+1), (AttributeValue) userRequestBody.get("param"+(i+1)));
    //        }
    //        item.put("requestBody",new AttributeValue().withM(req));

    /*
    item.put("Cron",new AttributeValue((String) requestBody.get("Cron")));
    item.put("method",new AttributeValue((String) requestBody.get("method")));
    PutItemRequest putItemRequest = new PutItemRequest("Schedules",item);
    PutItemResult putItemResult = dynamoDB.getAmazonDynamoDbClient().putItem(putItemRequest);
    */

    //        Item item = new Item().withPrimaryKey("ID", UUID.randomUUID().toString())
    //                .withString("URL", requestBody.get("URL").toString())
    //                .withStringSet(
    //                        "RequestBody",
    //                        new
    // HashSet<String>(Arrays.asList(requestBody.get("RequestBody").toString())))
    //                .withString("Cron", requestBody.get("Cron").toString());
    //        table.putItem(item);
    //        GetItemRequest getItemRequest =  new GetItemRequest().withTableName("tableName")
    //                .withKey(partitionKey, sortKey);
    //        GetItemResult result = dynamoDBClient.getItem(getItemRequest);
    //        Map<String, AttributeValue> item = result.getItem();
    //
      QuerySpec spec = new QuerySpec()
              .withKeyConditionExpression("UserName = :v_name and ScheduleName = :v_schedule")
              .withValueMap(new ValueMap()
                      .withString(":v_name", "arun")
                      .withString(":v_schedule", "firstTest"))
              .withConsistentRead(true);
      ItemCollection<QueryOutcome> items = table.query(spec);

//      QuerySpec spec = new QuerySpec()
//              .withKeyConditionExpression("UserName = :v_name")
//              .withValueMap(new ValueMap()
//                      .withString(":v_name", "arun"))
//              .withConsistentRead(true);
      Iterator<Item> iterator = items.iterator();
      while (iterator.hasNext()) {
          System.out.println(iterator.next().toJSONPretty());
      }

  }

}