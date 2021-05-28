package com.example.demo.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.example.demo.model.Story;
import com.example.demo.model.Tag;
import com.example.demo.service.StoryService;
import com.example.demo.service.TagService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class StoryController {
    @Autowired
    StoryService storyService;

    @Autowired
    TagService tagService; 

    @GetMapping(path = "/story/{id}")
    String story(@PathVariable int id, Model model) {
        Story story
            = storyService.findById(id);
        if (story == null) {
            model.addAttribute("error", "story not found");
            return "stories";
        }
        model.addAttribute("story", story);
        return "story";
    }

    @GetMapping(path = "/post")
    String post(HttpServletRequest request) {
        String username = GeneralController
            .getUsernameFromCookie(request);
        if (username == null) return "redirect:/login";
        return "post";
    }

    @PostMapping(path = "/post")
    String post(HttpServletRequest request, Model model) {
        String username = GeneralController
            .getUsernameFromCookie(request);
        if (username == null) return "redirect:/login";
        String title = request.getParameter("title");
        String tags = request.getParameter("tags");
        String content = request.getParameter("content");
        Story story = new Story();
        List<String> error = new ArrayList<>();
        if (title == null || title == "") {
            error.add("title cannot be empty");
        }
        else story.withTitle(title);
        if (tags == null || tags == "") ;
        else for (String word : tags.split(" ")) {
            String tag = word.trim().toLowerCase();
            story.addTags(new Tag().link(story)
                .withName(tag)
                .withHref("/tags/" + tag));
        }
        if (content == null || content == "") {
            error.add("content cannot be empty");
        }
        else story.withContent(content);
        if (error.size() > 0) {
            model.addAttribute("story", story);
            model.addAttribute("error", error);
            System.out.println(model);
            return "post";
        }
        story.postAt(LocalDateTime.now());
        for (Tag tag : story.tags) {
            try {
                tagService.save(tag);
            } catch (Exception ex) { // SQLException
                System.out.println(ex.getMessage());
            }
        }
        try {
            int id = storyService.save(story);
            model.addAttribute("id", id);
            return "redirect:/story/" + id;
        } catch (Exception ex) { // SQLException
            System.out.println(ex.getMessage());
        }
        return "redirect:/stories";
    }
}
