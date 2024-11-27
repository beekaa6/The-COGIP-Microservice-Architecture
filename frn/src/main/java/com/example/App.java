package com.example;

import java.util.List;

public class App {
  public static void main(String[] args) {
    System.out.println();
    if (args.length == 0) {
      System.out.println("No arguments");
    } else if (args[0].equals("list")) {
      list(args);
    } else if (args[0].equals("add")) {
      add(args);
    } else if (args[0].equals("delete")) {
      delete(args);
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

  public static void add(String args[]){
    if (args.length < 2) {
      System.out.println("Missing required arguments");
      System.out.println("Available Options: company, contact, invoice, user");
    } else {
      String urlPath = args[1].toLowerCase();
      if (List.of("company", "contact", "invoice", "user").contains(urlPath)) {
        String name = null;
        String country = null;
        String vat = null;
        String companyType = null;

        for (int i = 2; i < args.length; i++) {
          if (args[i].startsWith("--name=")) {
            name = args[i].substring("--name=".length());
          } else if (args[i].startsWith("--country=")) {
            country = args[i].substring("--country=".length());
          } else if (args[i].startsWith("--vat=")) {
            vat = args[i].substring("--vat=".length());
          } else if (args[i].startsWith("--companyType=")) {
            companyType = args[i].substring("--companyType=".length());
          }
        }

        if (name == null || country == null || vat == null || companyType == null) {
            System.out.println("Missing required subcommands and flags");
            System.out.println("Available Options: ");
            System.out.print("   --name=\"MyCompany\" ");
            System.out.print("--country=\"Belgium\" ");
            System.out.print("--vat=\"12345BE\" ");
            System.out.print("--companyType=\"CLIENT\" ");
        } else {
          System.out.println(name + " " + country + " " + vat + " " + companyType);
          //for now let like this, before i send actual data
        }
        
        // else {
        //   System.out.println("Missing required subcommands and flags");
        //   System.out.println("Available Options: ");
        //   System.out.println("\t--name=\"Company Name\"");
        //   System.out.print("--country=\"Company Country\"");
        //   System.out.print("--vat=\"Company Vat\"");
        //   System.out.print("--companyType=\"CompanyType\"");
        // }

      } else {
        System.out.println("Missing required arguments");
        System.out.println("Available Options: company, contact, invoice, user");
      }
    }
  }

  public static void delete(String args[]){
    System.out.println(args[0]);
    //tba
  }
}
