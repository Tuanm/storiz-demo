package com.example.demo.controller;

import com.example.demo.model.Tag;
import com.example.demo.service.StoryService;
import com.example.demo.service.TagService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class TagsController {
    @Autowired
    TagService tagService;

    @Autowired
    StoryService storyService;

    @GetMapping(path = "/tags/{name}")
    String tags(@PathVariable String name, Model model) {
        Tag tag = tagService.findById(name);
        if (tag == null) {
            model.addAttribute("error", "story not found");
            model.addAttribute("stories", storyService.findAll());
            return "stories";
        }
        model.addAttribute("stories", tag.stories);
        return "stories";
    }

    @GetMapping(path = "/tags")
    String tags(Model model) {
        model.addAttribute("stories", storyService.findAll());
        return "stories";
    }
}
