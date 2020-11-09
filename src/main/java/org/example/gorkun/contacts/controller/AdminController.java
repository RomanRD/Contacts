package org.example.gorkun.contacts.controller;

import org.example.gorkun.contacts.entity.Contact;
import org.example.gorkun.contacts.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

@Controller
public class AdminController {

    @Autowired
    ContactService contactService;

    @PostMapping("/contact/add")
    public String addContact(@RequestParam(name="img", required = false) MultipartFile photo,
                             @Valid @ModelAttribute("contact") Contact contact,
                             BindingResult bindingResult) {

        if(bindingResult.hasErrors()){
            return "redirect:/";
        }

        contactService.addContact(contact, photo);
        return "redirect:/";
    }

    @GetMapping("/contact/{id}/edit")
    public String editContact(@PathVariable Long id, Model model){
        model.addAttribute("contact", contactService.getContact(id));
        return "edit_contact";
    }

    @PostMapping("/contact/{id}/edit")
    public String editContact(@PathVariable long id,
                              @RequestParam(name="img", required = false) MultipartFile photo,
                              @Valid @ModelAttribute("contact") Contact contactEdit,
                              BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "edit_contact";
        }
        contactService.editContact(id, contactEdit, photo);
        return "redirect:/";
    }

    @DeleteMapping("/contact/{id}/delete")
    public String deleteContact(@PathVariable long id){
        contactService.deleteContact(id);
        return "redirect:/";
    }
}
