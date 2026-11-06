
package com.rosidha.portfolio_backend.service;

import com.rosidha.portfolio_backend.model.Contact;
import com.rosidha.portfolio_backend.repository.ContactRepository;
import org.springframework.stereotype.Service;

@Service
public class ContactService {
public java.util.List<Contact> getAllContacts() {
    return contactRepository.findAll();
}
    private final ContactRepository contactRepository;

    public ContactService(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    public Contact saveContact(Contact contact) {
        return contactRepository.save(contact);
    }
}

