package com.akagera.park.controller;

import com.akagera.park.model.Form;
import com.akagera.park.services.EmailService;
import com.akagera.park.services.FormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class FormController {
    private final FormService formService;


    public FormController(FormService formService) {

        this.formService = formService;
    }
    @Autowired
    private EmailService emailService;

    @GetMapping("/createForm")
  public String getFormForm(Model model){
        Form form = new Form();
        model.addAttribute("form",form);
        return "createForm";
    }
    @PostMapping("/createForm")
    public String createForm(@ModelAttribute("form")Form form){
        String to="uwasemarie2000@gmail.com";
        String subject="Car Bought";
        String body="A car has been bought. Progress has been made. Thank you";
        emailService.sendRegistrationEmail(to,subject,body);
        formService.saveForm(form);
        return "redirect:/table";
    }

    @GetMapping("/delete/{id}")
    public String deleteCar(@PathVariable("id")int id){
        formService.deleteFormById(id);
        return "redirect:/table";
    }

    @GetMapping("/update/{id}")
    public  String updateCar(@PathVariable("id")int id, Model model){
        System.out.println("ID from URL: " + id);
        model.addAttribute("form", formService.getFormById(id).orElse(null));
        return "edit-form";

    }

    @PostMapping("/update")
    public String updateCarForm(@ModelAttribute Form form){
        formService.saveForm(form);
        return "redirect:/table";
    }
//
//    @GetMapping("/edit/{id}")
//    public String getEditForm(@PathVariable("id") int id, Model model){
//        model.addAttribute("data", formService.getFormById(id));
//        return "edit-form";
//    }


//    @PostMapping("/edit")
//    public String editForm(@ModelAttribute("form")Form form){
//        formService.saveForm(form);
//        return "redirect:/admin/dashboard";}



}
