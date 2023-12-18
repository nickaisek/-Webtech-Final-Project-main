package com.akagera.park.controller;

import com.akagera.park.model.User;
import com.akagera.park.services.EmailService;
import com.akagera.park.services.FormService;
import com.akagera.park.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class AuthController {
    @Autowired
    private EmailService emailService;
    @Autowired
    UserService userService;
    private final FormService formService;

    public AuthController(FormService formService) {
        this.formService = formService;
    }
//    private User user;

    @RequestMapping(value = {"/login"}, method = RequestMethod.GET)
    public String login(){
        return "auth/login";
    }

    @RequestMapping(value = {"/register"}, method = RequestMethod.GET)
    public String register(Model model){
        model.addAttribute("user", new User());
        return "auth/register";
    }

    @RequestMapping(value = {"/register"}, method = RequestMethod.POST)
    public String registerUser(Model model, User user, BindingResult bindingResult){
        System.out.println(
                "helllo"
        );
        if(bindingResult.hasErrors()){
            model.addAttribute("successMessage", "User registered successfully!");
            model.addAttribute("bindingResult", bindingResult);
            return "auth/register";
        }
        List<Object> userPresentObj = userService.isUserPresent(user);
        if((Boolean) userPresentObj.get(0)){
            model.addAttribute("successMessage", userPresentObj.get(1));
            return "auth/register";
        }

        userService.saveUser(user);
        model.addAttribute("successMessage", "User registered successfully!");

        return "auth/login";
    }

//    @GetMapping("/table")
//    public String getTable(){
//        return "table";
//    }

    @RequestMapping(value = {"/admin/dashboard"}, method = RequestMethod.GET)
    public String adminHome() {
//        model.addAttribute("data",formService.getAllForms());
        return "admin/dashboard";
    }

    @GetMapping("/ford")
    public String getFord(){
        return "user/ford";
    }
    @GetMapping("/bmw")
    public String getBmw(){
        return "user/bmw";
    }

    @GetMapping("/hyundai")
    public String getHyundai(){
        return "user/hyundai";
    }

    @GetMapping("/kicks")
    public String getKicks(){
        return "user/kicks";
    }

    @GetMapping("/mercedes")
    public String getMercedes(){
        return "user/mercedes";
    }

    @GetMapping("/renault")
    public String getRenault(){
        return "user/renault";
    }

    @GetMapping("/suzuki")
    public String getSuzuki(){
        return "user/suzuki";
    }

    @GetMapping("/vwcar")
    public String getVwcar(){
        return "user/vwcar";
    }

    @GetMapping("/bye")
    public String getBye(){
        String to="uwasemarie2000@gmail.com";
        String subject="Car Bought";
        String body="A car has been bought. Progress has been made. Thank you";
        emailService.sendRegistrationEmail(to,subject,body);
        return "user/bye";
    }
    @GetMapping("/table")
    public String getDashboard(Model model){
        model.addAttribute("form" , formService.getAllForms());
        return "/table";
    }










}

