package com.example.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@Entity
public class Contact {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  @NonNull
  private String firstName;
  @NonNull
  private String lastName;
  @NonNull
  private String phone;
  @NonNull
  private String email;
  @ManyToOne
  @JoinColumn(name = "contact_company_id", referencedColumnName = "id")
  private Company company;
}
