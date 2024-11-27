package com.example;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Requests {
  private String url = "https://localhost:8080/";
  private HttpClient client = HttpClient.newHttpClient();

  public void getList(String urlPath){
    try {
      HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).GET().build();

      HttpResponse<String> response = this.client.send(request, HttpResponse.BodyHandlers.ofString());
      
      System.out.println("Response Status: "+response.statusCode());
      System.out.println("Response Body: ");
      System.out.println(response.body());
    } catch (Exception e) {
      System.out.println("Error: "+e.getMessage());
    }
  }

}
