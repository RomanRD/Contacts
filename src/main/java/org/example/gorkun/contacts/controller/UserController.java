package org.example.gorkun.contacts.controller;

import org.example.gorkun.contacts.entity.Contact;
import org.example.gorkun.contacts.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class UserController {

    @Autowired
    ContactService contactService;

    @GetMapping(value = {"/", "/contacts"})
    public String contacts(Model model){
        model.addAttribute("contact", new Contact());
        model.addAttribute("contacts", contactService.getAllContacts());
        return "contacts_list";
    }

}
