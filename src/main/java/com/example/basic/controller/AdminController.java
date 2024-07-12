package com.example.basic.controller;

import org.springframework.web.bind.annotation.GetMapping;

public class AdminController {
    @GetMapping("/admin")
    public String mainP() {

        return "admin";
    }
}
