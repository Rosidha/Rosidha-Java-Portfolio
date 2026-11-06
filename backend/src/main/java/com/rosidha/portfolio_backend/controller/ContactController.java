
package com.rosidha.portfolio_backend.controller;

import com.rosidha.portfolio_backend.model.Contact;
import com.rosidha.portfolio_backend.service.ContactService;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/contacts")
@CrossOrigin(origins = "*")
public class ContactController {

    private final ContactService contactService;

    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @PostMapping
    public ResponseEntity<Contact> saveContact(
            @Valid @RequestBody Contact contact) {

        Contact savedContact = contactService.saveContact(contact);

        return new ResponseEntity<>(savedContact, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<?> getAllContacts() {
        return ResponseEntity.ok(
                contactService.getAllContacts()
        );
    }
}

