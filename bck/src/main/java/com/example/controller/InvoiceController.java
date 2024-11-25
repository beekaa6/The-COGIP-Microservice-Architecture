package com.example.controller;

import java.util.List;
import java.util.Optional;
import com.example.model.Invoice;
import com.example.service.InvoiceService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/invoice")
public class InvoiceController {
  private final InvoiceService invoiceService;
  public InvoiceController(InvoiceService invoiceService) {
    this.invoiceService = invoiceService;
  }

  @GetMapping
  public List<Invoice> getAll(){
    return invoiceService.getAllInvoice();
  }

  @GetMapping("/{id}")
  public Optional<Invoice> getById(@PathVariable int id){
    return invoiceService.getByIdInvoice(id);
  }

  @PostMapping
  public void addNew(@RequestBody Invoice invoice){
    invoiceService.saveInvoice(invoice);
  }

  @PutMapping
  public void updateNew(@RequestBody Invoice invoice){
    invoiceService.saveInvoice(invoice);
  }

  @DeleteMapping("/{id}")
  public void deleteById(@PathVariable int id){
    invoiceService.deleteByIdInvoice(id);
  }

}
