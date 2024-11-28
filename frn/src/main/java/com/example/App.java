package com.example;

import java.util.List;
import java.util.Map;

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
      if (urlPath.contains("company")) {
        addCompany(args, urlPath);
      } else if (urlPath.contains("contact")) {
        addContact(args, urlPath);
      } else {
        System.out.println("Missing required arguments");
        System.out.println("Available Options: company, contact, invoice, user");
      }
    }
  }

  public static void delete(String args[]){
    //to be added heh
  }


  // ADD METHODS >>>>>>

  public static String flagValue(String[] args, String flag) {
    for (String argument : args) {
      if (argument.startsWith(flag)) {
        return argument.substring(flag.length());
      }
    }
    return null;
  }

  public static void addCompany(String[] args, String urlPath){
    String name = flagValue(args, "--name=");
    String country = flagValue(args, "--country=");
    String vat = flagValue(args, "--vat=");
    String type = flagValue(args, "--type=");

    if (name == null || country == null || vat == null || type == null) {
        System.out.println("Missing required subcommands and flags");
        System.out.println("Available Options: ");
        System.out.print("   --name=\"MyCompany\" ");
        System.out.print("--country=\"Belgium\" ");
        System.out.print("--vat=\"12345BE\" ");
        System.out.print("type=\"CLIENT\" ");
    } else if (!List.of("CLIENT", "PROVIDER").contains(type.toUpperCase())) {
      System.out.println("Invalid value for 'type': "+type);
      System.out.println("Available Options: CLIENT, PROVIDER");
    } else {
      Map<String, Object> dataList = Map.of(
        "name", name,
        "country", country,
        "vat", vat,
        "type", type.toUpperCase()
      );
      Requests requests = new Requests();
      requests.addNewData(urlPath, dataList);
    }
  }

  public static void addContact(String[] args, String urlPath){
    String firstName = flagValue(args, "--firstName=");
    String lastName = flagValue(args, "--lastName=");
    String phone = flagValue(args, "--phone=");
    String email = flagValue(args, "--email=");
    String id = flagValue(args, "--contactCompany=");

    if (firstName == null || lastName == null || phone == null || email == null || id == null) {
        System.out.println("Missing required subcommands and flags");
        System.out.println("Available Options: ");
        System.out.print("   --firstName=\"Alex\" ");
        System.out.print("--lastName=\"Mahone\" ");
        System.out.print("--phone=\"0412345678\" ");
        System.out.print("--email=\"alex@alex.alex\" ");
        System.out.print("--contactCompany=\"1\" ");
    } else {
      Map<String, String> contactCompany = Map.of(
        "id", id
      );

      Map<String, Object> dataList = Map.of(
        "firstName", firstName,
        "lastName", lastName,
        "phone", phone,
        "email", email,
        "contactCompany", contactCompany
      );
      Requests requests = new Requests();
      requests.addNewData(urlPath, dataList);
    }
  }

  public static void addInvoice(String[] args, String urlPath){
    //to be added heh
  }

  public static void addUser(String[] args, String urlPath){
    //to be added heh
  }
}
