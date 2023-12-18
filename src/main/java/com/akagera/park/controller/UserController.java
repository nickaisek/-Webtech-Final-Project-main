package com.akagera.park.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UserController {
    @RequestMapping(value = {"/dashboard"}, method = RequestMethod.GET)
    public String homePage(){
        return "user/dashboard";
    }

    @GetMapping("/buy")
    public String getBuyForm(){
        return "user/buy";
    }

    @GetMapping("/pay")
    public String getPayForm(){
        return "user/pay";
    }

}
