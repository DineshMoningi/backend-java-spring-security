package com.learn.security.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/public")
public class SharedController {
  @GetMapping("/home")
  public String home() {
    return "Your in home page";
  }

  @GetMapping("/register")
  public String register() {
    return "Your in register page";
  }

  @GetMapping("/login")
  public String login() {
    return "Your in login page";
  }
}
