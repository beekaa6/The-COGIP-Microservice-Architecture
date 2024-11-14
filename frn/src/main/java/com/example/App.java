package com.example;

import java.util.List;
import java.util.Scanner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;


@SpringBootApplication
public class App implements CommandLineRunner{
  private final RestTemplate restTemplate = new RestTemplate();
  private final Scanner scanner = new Scanner(System.in);
  private String url = "http://localhost:8080/companies";

  public static void main(String[] args) {
    SpringApplication.run(App.class, args);
  }

  @Override
  public void run(String... args){
    while (true) {
      System.out.print("Command: ");
      String input = scanner.nextLine().toLowerCase().trim();
      if (input.equals("quit") || input.isEmpty()) {
        System.out.println("Quitting");
        break;
      }
      if (input.equals("list")) {
        for (CompanyDTO co : getAllList()) {
          System.out.println(co);
        }
      }
    }
  }

  public List<CompanyDTO> getAllList(){
    ResponseEntity<List<CompanyDTO>> response = restTemplate.exchange(
      url, 
      HttpMethod.GET,
      null,
      new ParameterizedTypeReference<List<CompanyDTO>>() {}
    );
    return response.getBody();
  }

  public void postList(){
    CompanyDTO company = new CompanyDTO("bob", 12345);
  }
}
