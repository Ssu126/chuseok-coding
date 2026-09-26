package com.example.chuseok_coding.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UserController {
    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public String userPage() {
        return "/users/list";
    }

    @RequestMapping(value = "/users/1/detail", method = RequestMethod.GET)
    public String detailPage() {
        return "/users/detail";
    }
}