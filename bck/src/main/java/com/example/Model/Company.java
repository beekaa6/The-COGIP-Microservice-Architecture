package com.example.model;


import java.time.LocalDateTime;
import org.hibernate.annotations.CreationTimestamp;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

//* */
@Data
@NoArgsConstructor
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
  private String vat;
  @NonNull
  @Enumerated(EnumType.STRING)
  private CompanyType type;
  @CreationTimestamp
  @Column(updatable = false)
  private LocalDateTime timestamp;
}
