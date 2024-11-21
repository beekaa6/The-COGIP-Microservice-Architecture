package com.example.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;


@RequiredArgsConstructor
@Setter
@Getter
@Entity
public class Invoice {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  private String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm' - 'dd/MM/yy"));
  @ManyToOne
  @JoinColumn(name = "invoice_company_id", referencedColumnName = "id")
  private Company companyId;
  @ManyToOne
  @JoinColumn(name = "invoice_contact_id", referencedColumnName = "id")
  private Contact contactId;
}
