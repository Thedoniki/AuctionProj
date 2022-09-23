package com.example.auctionp.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class C_Auth {

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @RequestMapping("/logout-success")
    public String logout() {
        return "login";
    }

    @RequestMapping("/")
    public String redirect() {
        return "redirect:/1";
    }

}
