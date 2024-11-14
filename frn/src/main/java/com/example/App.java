package com.example;

import java.util.List;
import java.util.Scanner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;
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
        try {
          for (CompanyDTO co : getAllList()) {
            System.out.println(co);
          }
        } catch (Exception e) {
          System.out.println("Error: "+e.getMessage());
        }
      }
      if (input.equals("add")) {
        String name;
        int vatNumber;
        while (true) {
          System.out.print("Name: ");
          name = scanner.nextLine().trim();
          if (name.isEmpty() || name.length() < 2) {
            System.out.println("Invalid name");
            continue;
          }
          System.out.print("VAT Number: ");
          try {
            vatNumber = Integer.parseInt(scanner.nextLine().trim());
            if (String.valueOf(vatNumber).length() < 4) {
                System.out.println("Invalid VAT Number");
                continue;
            }
          } catch (NumberFormatException e) {
              System.out.println("Enter valid VAT Number.");
              continue;
          }
          postList(name, vatNumber);
          break;
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

  public void postList(String name, int vatNumber) {
    HttpEntity<CompanyDTO> company = new HttpEntity<CompanyDTO>(new CompanyDTO(name, vatNumber));
     try {
        restTemplate.exchange(
            url, 
            HttpMethod.POST,
            company,
            new ParameterizedTypeReference<String>() {}
        );
        System.out.println("Company added.");
    } catch (RestClientException e) {
        System.out.println("Error: " + e.getMessage());
    }
  }
}
