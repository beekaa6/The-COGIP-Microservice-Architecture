package com.example;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Base64;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Requests {
  private String url = "http://localhost:8080/";
  private HttpClient client = HttpClient.newHttpClient();

  private String username = "beka";
  private String password = "test";

  public String basicEncoding(){
    String auth = username +":"+ password;
    return "Basic " + Base64.getEncoder().encodeToString(auth.getBytes());
  }

  public void formatJson(String result){
    Gson gson = new GsonBuilder().setPrettyPrinting().create();
    Object gsonObject = gson.fromJson(result, Object.class);
    System.out.println(gson.toJson(gsonObject));
  }

  public void getList(String urlPath){
    try {
      HttpRequest request = HttpRequest.newBuilder()
        .uri(URI.create(url + urlPath))
        .GET()
        .header("Authorization", basicEncoding())
        .build();

      HttpResponse<String> response = this.client.send(request, HttpResponse.BodyHandlers.ofString());
      
      System.out.println("Response Status: "+response.statusCode());
      if (response.statusCode() != 200) {
        System.out.println("Error: Received status code " + response.statusCode());
      } else {
        System.out.println("Response Body: ");
        //System.out.println(response.body());
        formatJson(response.body());
      }
    
    } catch (Exception e) {
      System.out.println("Error: "+e.getMessage());
      System.out.println("URL: " + url + urlPath);
    }
  }
}
