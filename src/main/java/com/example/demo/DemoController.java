package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Controller
public class DemoController {

    @RequestMapping("/")
    public String root(){

        return "login";
    }

    @RequestMapping("/login")
    public String login(){

        return "login";
    }

}
