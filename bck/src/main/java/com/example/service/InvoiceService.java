package com.example.service;

import java.util.List;
import java.util.Optional;
import com.example.model.Invoice;
import com.example.repository.InvoiceRepository;
import org.springframework.stereotype.Service;


@Service
public class InvoiceService {
  private final InvoiceRepository repo;
  public InvoiceService(InvoiceRepository repo) {
    this.repo = repo;
  }

  public List<Invoice> getAllInvoice(){
    return repo.findAll();
  }

  public Optional<Invoice> getByIdInvoice(int id){
    return repo.findById(id);
  }

}
