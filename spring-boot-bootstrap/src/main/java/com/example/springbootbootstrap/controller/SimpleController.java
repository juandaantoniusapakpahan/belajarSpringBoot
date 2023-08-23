package com.example.springbootbootstrap.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SimpleController {
    @Value("${spring.application.name}")
    String appName;



    @RequestMapping("/")
    public String homePage(Model model){
        model.addAttribute("appName", appName);
        return "home";
    }

    @RequestMapping("/about")
    public String aboutPage(Model model){
        return "about";
    }
}
