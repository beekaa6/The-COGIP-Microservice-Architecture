package com.example;

import java.util.List;

public class App {
  public static void main(String[] args) {
    if (args.length == 0) {
      System.out.println("No args");
    } else if (args[0].equals("list")) {
      list(args);
    } else if (args[0].equals("delete")) {
      delete(args);
    } else if (args[0].equals("add")) {
      add(args);
    } else {
      System.out.println("Unknown command: "+args[0]);
    }
  }

  public static void list(String args[]){
    if (args.length < 2) {
      System.out.println("Missing required arguments");
      System.out.println("Example: list company, or contact, or invoice");
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

  public static void add(String args[]){
    if (args.length < 2) {
      System.out.println("Missing required arguments");
      System.out.println("Example: add company --name=<NAME> --vat=<VAT>");
    }  else {
      if (args[1].equals("company")) {
        String name = null;
        String vat = null;
        for (int i = 2; i < args.length; i++) {
          if (args[i].startsWith("--name=")) {
            name = args[i].substring("--name=".length());
          } else if (args[i].startsWith("--vat=")) {
            vat = args[i].substring("--vat=".length());
          }
        }
        if (name == null || vat == null) {
          System.out.println("Arguments required");
        } else {
          System.out.println("Adding company: ");
          System.out.println("Company Name: "+name);
          System.out.println("Company Vat: "+vat);
          //tba
        }
      } else {
        System.out.println("Unknown Arguments");
      }
    }
  }

  public static void delete(String args[]){
    System.out.println(args[0]);
    //tba
  }
}
