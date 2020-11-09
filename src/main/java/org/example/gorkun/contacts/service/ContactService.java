package org.example.gorkun.contacts.service;

import org.example.gorkun.contacts.entity.Contact;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ContactService {

    @Secured("ROLE_ADMIN")
    Contact addContact(Contact contact, MultipartFile photo);

    @Secured("ROLE_ADMIN")
    Contact editContact(long id, Contact contactEdit, MultipartFile photo);

    @Secured("ROLE_ADMIN")
    void deleteContact(long id);

    Contact getContact(long id);

    List<Contact> getAllContacts();
}
