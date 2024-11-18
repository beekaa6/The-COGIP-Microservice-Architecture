package com.example.controller;

import com.example.model.Contact;
import com.example.service.ContactService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RestController("/contact")
public class ContactController {
    private final ContactService contactService;
    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @GetMapping
    public List<Contact> getContacts(){
        return contactService.getContact();
    }

    @GetMapping("/{id}")
    public Optional<Contact> getContactsById(@PathVariable int id){
        return  contactService.getContactById(id);
    }

    @PostMapping
    public void addContact(@RequestBody Contact contact){
        contactService.saveContact(contact);
    }

    @DeleteMapping("/{id}")
    public void deleteContact(@PathVariable int id){
        contactService.deleteContactById(id);
    }
}
