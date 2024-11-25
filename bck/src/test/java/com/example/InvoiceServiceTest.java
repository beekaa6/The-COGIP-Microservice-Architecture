package com.example;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.util.Optional;

import com.example.model.Invoice;
import com.example.repository.InvoiceRepository;
import com.example.service.InvoiceService;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class InvoiceServiceTest {
  @Mock
  private InvoiceRepository invoiceRepository;

  @InjectMocks
  private InvoiceService invoiceService;

  @Before
  public void before(){
    MockitoAnnotations.openMocks(this);
  }

  @Test
  public void testGetInvoice(){
    Invoice mockInvoice = new Invoice();
    mockInvoice.setNumber("INV123");

    when(invoiceRepository.findById(1)).thenReturn(Optional.of(mockInvoice));

    Optional<Invoice> result = invoiceService.getByIdInvoice(1);

    assertTrue(result.isPresent());
    assertEquals("INV123", result.get().getNumber());
  }

}
