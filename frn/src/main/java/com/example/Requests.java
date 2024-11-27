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

      //System.out.println(response.body());
      if (response.statusCode() == 200) {
        System.out.println("Response Body: ");
        formatJson(response.body());
      } else if (response.statusCode() == 403) {
        System.out.println("Response Status: "+response.statusCode());
        System.out.println("Access Denied: You do not have permission to view this page.");
      } else {
        System.out.println("Error: Received status code " + response.statusCode());
      }
    
    } catch (Exception e) {
      System.out.println("Error: "+e.getMessage());
      System.out.println("URL: " + url + urlPath);
    }
  }
}
