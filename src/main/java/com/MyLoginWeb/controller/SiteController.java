package com.MyLoginWeb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.swing.text.html.HTMLDocument;

public class SiteController {
    @GetMapping("/welcome")
    public String welcomePage(Model model){
        model.addAttribute("username","AnkanDas56");
        model.addAttribute("isAdmin",true);
        return "index";
    }
    public String getUserName(Model model){
        return model.getAttribute("username").toString();
    }
}
