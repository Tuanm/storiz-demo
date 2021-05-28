package com.example.demo.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.demo.config.LoginConfig;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {
    @GetMapping(path = "/login")
    String login(HttpServletRequest request) {
        String username = GeneralController
            .getUsernameFromCookie(request);
        if (username != null
            && LoginConfig.USERNAME.equals(username)) {
            return "forward:/stories";
        }
        return "login";
    }

    @PostMapping(path = "/login")
    String login(HttpServletRequest request,
        HttpServletResponse response, Model model) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        if (username != null && password != null
            && LoginConfig.USERNAME.equals(username)
            && LoginConfig.PASSWORD.equals(password)) {
            Cookie cookie = new Cookie("username", username);
            cookie.setMaxAge(300); // 300 seconds = 5 minutes
            response.addCookie(cookie);
            return "redirect:/post";
        }
        model.addAttribute("error", "username or password incorrect");
        return "redirect:/login";
    }
}
