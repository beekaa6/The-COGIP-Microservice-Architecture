package com.example.service;

import java.util.List;
import java.util.Optional;
import com.example.model.Contact;
import com.example.repository.ContactRepository;
import org.springframework.stereotype.Service;


@Service
public class ContactService {
  private final ContactRepository repo;
  public ContactService(ContactRepository repo) {
    this.repo = repo;
  }
  
  public List<Contact> getContact(){
    return repo.findAll();
  }
  
  public Optional<Contact> getContactById(int id){
    return repo.findById(id);
  }

  public void saveContact(Contact contact){
    if (contact != null) {
      repo.save(contact);
    }
  }

  public void deleteContactById(int id){
    repo.deleteById(id);
  }
}
