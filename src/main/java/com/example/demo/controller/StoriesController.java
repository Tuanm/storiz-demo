package com.example.demo.controller;

import com.example.demo.service.StoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StoriesController {
    @Autowired
    StoryService service;

    @GetMapping(path = "/stories")
    String stories(Model model) {
        model.addAttribute("stories", service.findAll());
        return "stories";
    }
}
