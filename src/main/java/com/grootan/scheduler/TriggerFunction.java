package com.grootan.scheduler;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import okhttp3.*;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.*;

public class TriggerFunction implements RequestHandler<Map<String, Object>, String> {
  public static final MediaType JSON = MediaType.get("application/json; charset=utf-8");
  static OkHttpClient client = new OkHttpClient();

  @Override
  public String handleRequest(Map<String, Object> input, Context context) {
    //        getPathVariable(input,pathVariable);
    String str = input.get("RequestBody").toString();
    JsonElement json = JsonParser.parseString(str);
    RequestBody body = RequestBody.create(json.toString(), JSON);
    String url = input.get("requestUrl").toString();
    Request request = new Request.Builder().url(url).post(body).build();
    Response response = null;
    try {
      response = client.newCall(request).execute();
      return response.body().string();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  public String getResponse(Map<String, Object> input) {
    String url = input.get("requestUrl").toString();
    Request request = new Request.Builder().url(url).build();
    Response response = null;
    try {
      response = client.newCall(request).execute();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    return response.body().toString();
  }

  public static String getPathVariable(
      Map<String, Object> input, Map<String, String> requestParam) {
    String url = input.get("requestUrl").toString();
    HttpUrl.Builder urlBuilder = HttpUrl.parse(url).newBuilder();
    for (Map.Entry<String, String> iterator : requestParam.entrySet()) {
      urlBuilder.addQueryParameter(iterator.getKey(), iterator.getValue());
    }

    System.out.println("URLBUILDER:  " + urlBuilder);

    Request request = new Request.Builder().url(url).build();
    Response response = null;
    try {
      response = client.newCall(request).execute();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
//    System.out.println("Response:   " +response.body().toString());
    return response.toString();
  }
}
