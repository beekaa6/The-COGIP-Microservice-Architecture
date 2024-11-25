package com.example;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import com.example.model.Contact;
import com.example.repository.ContactRepository;
import com.example.service.ContactService;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class ContactServiceTest {
  @Mock
  private ContactRepository contactRepository;

  @InjectMocks
  private ContactService contactService;

  @Before
  public void before(){
    MockitoAnnotations.openMocks(this);
  }

  @Test
  public void testContacts(){
    Contact mockContact = new Contact();
    mockContact.setFirstName("beka");
    mockContact.setLastName("biceps");
    mockContact.setPhone("0487654321");
    mockContact.setEmail("beka@beka.beka");

    when(contactRepository.findById(1)).thenReturn(Optional.of(mockContact));

    Optional<Contact> result = contactService.getContactById(1);

    assertTrue(result.isPresent());
    assertEquals("beka", result.get().getFirstName());
    assertEquals("biceps", result.get().getLastName());
    assertEquals("0487654321", result.get().getPhone());
    assertEquals("beka@beka.beka", result.get().getEmail());
  }

  @Test
  public void testContactsSaves(){
    Contact testIfSavingContact = new Contact();
    testIfSavingContact.setFirstName("save");
    testIfSavingContact.setLastName("testing");
    testIfSavingContact.setPhone("0412345678");
    testIfSavingContact.setEmail("save@save.save");

    when(contactRepository.save(testIfSavingContact)).thenReturn(testIfSavingContact);

    contactService.saveContact(testIfSavingContact);

    verify(contactRepository, times(1)).save(testIfSavingContact);
  }

  @Test
  public void testContactsIfEmpty(){
    when(contactRepository.findById(999)).thenReturn(Optional.empty());

    Optional<Contact> result = contactService.getContactById(999);

    assertFalse(result.isPresent());
  }
}
