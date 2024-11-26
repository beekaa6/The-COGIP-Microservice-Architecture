package com.example;

public class App {
  public static void main(String[] args) {
    if (args.length == 0) {
      System.out.println("No args");
    } else if (args[0].equals("add")) {
      add(args);
    } else if (args[0].equals("delete")) {
      add(args);
    } else if (args[0].equals("list")) {
      add(args);
    }

  }

  public static void add(String args[]){
    if (args.length < 2) {
      System.out.println("Missing required arguments");
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
        }
      } else {
        System.out.println("Unknown Arguments");
      }
    }
  }

  public static void delete(String args[]){
    System.out.println(args[0]);
  }

  public static void list(String args[]){
    System.out.println(args[0]);
  }
}
