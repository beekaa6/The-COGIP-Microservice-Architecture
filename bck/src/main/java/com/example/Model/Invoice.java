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


@Data
@Entity
public class Invoice {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  @Column(nullable = true, unique = true)
  private String number;
  @CreationTimestamp
  @Column(updatable = false)
  private LocalDateTime timestamp;
  @ManyToOne
  @JoinColumn(name = "invoice_company_id", referencedColumnName = "id")
  private Company companyId;
  @ManyToOne
  @JoinColumn(name = "invoice_contact_id", referencedColumnName = "id")
  private Contact contactId;
}
