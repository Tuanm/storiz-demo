package com.example.demo.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

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

    static String getUsernameFromCookie(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies == null) return null;
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("username")) {
                return cookie.getValue();
            }
        }
        return null;
    }
}
