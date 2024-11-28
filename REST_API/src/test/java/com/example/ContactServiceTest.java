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
  public void testContactsIfEmpty(){
    when(contactRepository.findById(999)).thenReturn(Optional.empty());

    Optional<Contact> result = contactService.getContactById(999);

    assertFalse(result.isPresent());
  }

  @Test
  public void testGetContacts(){
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
  public void testGetAllContact(){
    List<Contact> listContacts = List.of(
      new Contact("firstName1", "lastName1", "phone1", "email1"),
      new Contact("firstName2", "lastName2", "phone2", "email2")
    );

    when(contactRepository.findAll()).thenReturn(listContacts);

    List<Contact> result = contactService.getContact();

    assertNotNull(result);
    assertEquals(2, result.size());
    assertEquals("firstName1", result.get(0).getFirstName());
    assertEquals("lastName1", result.get(0).getLastName());
    assertEquals("phone1", result.get(0).getPhone());
    assertEquals("email1", result.get(0).getEmail());

    assertEquals("firstName2", result.get(1).getFirstName());
    assertEquals("lastName2", result.get(1).getLastName());
    assertEquals("phone2", result.get(1).getPhone());
    assertEquals("email2", result.get(1).getEmail());
  }

  @Test
  public void testSaveContacts(){
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
  public void testDeleteContacts(){
    contactService.deleteContactById(1);

    verify(contactRepository, times(1)).deleteById(1);
  }
}
