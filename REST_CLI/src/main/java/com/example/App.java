package com.example;

import java.util.List;

public class App {
  public static void main(String[] args) {
    Service service = new Service();
    System.out.println();
    if (args.length == 0) {
      System.out.println("No arguments");
    } else if (args[0].equals("list")) {
      list(args);
    } else if (args[0].equals("add")) {
      add(args, service);
    } else if (args[0].equals("delete")) {
      delete(args, service);
    } else {
      System.out.println("Unknown command: "+args[0]);
    }
    System.out.println();
  }

  public static void list(String args[]){
    if (args.length != 2) {
      System.out.println("Missing required arguments");
      System.out.println("Available Options: company, contact, invoice, user");
    } else {
      String urlPath = args[1].toLowerCase();
      if (List.of("company", "contact", "invoice", "user").contains(urlPath)) {
        Requests requests = new Requests();
        requests.getList(urlPath);
      } else {
        System.out.println("Unknown Argument: " + urlPath);
        System.out.println("Available Options: company, contact, invoice, user");
      }
    }
  }

  public static void add(String args[], Service service){
    if (args.length < 2) {
      System.out.println("Missing required arguments");
      System.out.println("Available Options: company, contact, invoice, user");
    } else {
      String urlPath = args[1].toLowerCase();
      if (urlPath.contains("company")) {
        service.addCompany(args, urlPath);
      } else if (urlPath.contains("contact")) {
        service.addContact(args, urlPath);
      } else if (urlPath.contains("invoice")) {
        service.addInvoice(args, urlPath);
      } else if (urlPath.contains("user")) {
        service.addUser(args, urlPath);
      } else {
        System.out.println("Missing required arguments");
        System.out.println("Available Options: company, contact, invoice, user");
      }
    }
  }

  public static void delete(String args[], Service service){
    if (args.length < 3) {
      System.out.println("Missing required arguments");
      System.out.println("Available Options: company, contact, invoice, user");
      System.out.println("Example: delete contact id");
    } else {
      try {
        String urlPath = args[1].toLowerCase();
        if (!List.of("company", "contact", "invoice", "user").contains(urlPath)) {
          System.out.println("Missing required arguments");
          System.out.println("Available Options: company, contact, invoice, user");
        }
        else {
          int id = Integer.parseInt(args[2]);
          urlPath = urlPath + "/" + id;
            
          Requests requests = new Requests();
          requests.deleteDataById(urlPath);
        }
          
      } catch (Exception e) {
        System.out.println("Invalid ID. It must be a numeric value.");
        System.out.println("Error message: "+e.getMessage());
      }
    }
  }
}
