package com.example.demo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoginConfig {
    public static String USERNAME;
    public static String PASSWORD;

    @Value(value = "${post.login.username}")
    public void setUsername(String username) {
        USERNAME = username;
    }

    @Value(value = "${post.login.password}")
    public void setPassword(String password) {
        PASSWORD = password;
    }
}
