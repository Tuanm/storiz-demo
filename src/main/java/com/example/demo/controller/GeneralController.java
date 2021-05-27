package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GeneralController {
    @GetMapping(path = "/")
    String index() {
        return "index";
    }

    @GetMapping(path = "/about")
    String about() {
        return "about";
    }

    @GetMapping(path = "/error")
    String error() {
        return "error";
    }
}
