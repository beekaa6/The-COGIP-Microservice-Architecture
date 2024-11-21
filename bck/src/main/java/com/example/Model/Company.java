package com.example.model;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
public class Company {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  @NonNull 
  private String name;
  @NonNull 
  private String country;
  @NonNull 
  @Column(unique = true)
  private Integer vat;
  @NonNull
  @Enumerated(EnumType.STRING)
  private CompanyType type;
  private String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm' - 'dd/MM/yy"));
}
