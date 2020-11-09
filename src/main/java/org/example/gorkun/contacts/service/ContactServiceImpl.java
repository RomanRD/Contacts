package org.example.gorkun.contacts.service;

import org.example.gorkun.contacts.entity.Contact;
import org.example.gorkun.contacts.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
public class ContactServiceImpl implements ContactService{
    @Autowired
    ContactRepository contactRepository;

    @Value("${upload.path}")
    private String uploadPath;

    @Override
    @Transactional
    public Contact addContact(Contact contact, MultipartFile photo) {
        if(!photo.isEmpty()){
            contact.setPhoto(uploadPhoto(photo));
        }

        contactRepository.save(contact);
        return contact;
    }

    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public Contact editContact(long id, Contact contactEdit, MultipartFile photo) {
        Contact contact = contactRepository.findById(id).get();
        contact.setName(contactEdit.getName());
        contact.setPhone(contactEdit.getPhone());

        if(!photo.isEmpty() && deletePhoto(contact)){
            contact.setPhoto(uploadPhoto(photo));
        }

        contactRepository.save(contact);
        return contact;
    }

    private String uploadPhoto(MultipartFile photo){
        File uploadDir = new File(uploadPath);
        uploadDir.mkdirs();
        String resultFilename = UUID.randomUUID().toString() + "." + photo.getOriginalFilename();
        try {
            photo.transferTo(new File(uploadDir.getPath() + "/" + resultFilename));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return resultFilename;
    }

    private boolean deletePhoto(Contact contact){
        File uploadDir = new File(uploadPath + "/" + contact.getPhoto());
        return uploadDir.delete();
    }

    @Override
    @Transactional
    public void deleteContact(long id) {
        Contact contact = contactRepository.findById(id).get();
        if(contact.getPhoto()!=null){
            deletePhoto(contact);
        }
        contactRepository.delete(contact);
    }

    @Override
    public Contact getContact(long id) {
        return contactRepository.findById(id).get();
    }

    @Override
    public List<Contact> getAllContacts() {
        return contactRepository.findAll();
    }
}
