package com.example.model;

import java.time.LocalDateTime;
import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;


@Data
@NoArgsConstructor
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
  @CreationTimestamp
  @Column(updatable = false)
  private LocalDateTime timestamp;
  @ManyToOne
  @JoinColumn(name = "contact_company_id", referencedColumnName = "id")
  private Company contactCompany;
}
