package com.example;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
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
  public void testIsPresentInvoice(){
    when(invoiceRepository.findById(999)).thenReturn(Optional.empty());

    Optional<Invoice> result = invoiceService.getByIdInvoice(999);

    assertFalse(result.isPresent());
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

  @Test
  public void testGetAllInvoice(){
    List<Invoice> listInvoices = List.of(
      new Invoice("INV321"),
      new Invoice("INV123")
    );

    when(invoiceRepository.findAll()).thenReturn(listInvoices);

    List<Invoice> result = invoiceService.getAllInvoice();

    assertNotNull(result);
    assertEquals(2, result.size());
    assertEquals("INV321", result.get(0).getNumber());
    assertEquals("INV123", result.get(1).getNumber());
  }

  @Test
  public void testSaveInvoice(){
    Invoice mockInvoice = new Invoice();
    mockInvoice.setNumber("INV9000");

    when(invoiceRepository.save(mockInvoice)).thenReturn(mockInvoice);

    invoiceService.saveInvoice(mockInvoice);

    verify(invoiceRepository, times(1)).save(mockInvoice);
  }

  @Test
  public void testDeleteInvoice(){
    invoiceService.deleteByIdInvoice(1);

    verify(invoiceRepository).deleteById(1);
  }
}
