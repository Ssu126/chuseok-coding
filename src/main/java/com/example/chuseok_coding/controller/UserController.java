package com.example.chuseok_coding.controller;

import com.example.chuseok_coding.service.User;
import com.example.chuseok_coding.service.UserServiceInterface;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserServiceInterface AUserService;

    @GetMapping
    public String userPage(Model model) {
        List<User> users = AUserService.findAll();
        model.addAttribute("users", users);
        return "/users/list";
    }

    @GetMapping(value = "/1/detail")
    public String detailPage(Model model) {
        User user = AUserService.findById(1);
        model.addAttribute("id", user.getId());
        model.addAttribute("name", user.getName());
        model.addAttribute("age", user.getAge());
        model.addAttribute("job", user.getJob());
        model.addAttribute("specialty", user.getSpecialty());
        return "/users/detail";
    }

    @ResponseBody
    @GetMapping(value = "/1/data")
    public User detailData() {
        User user = AUserService.findById(1);
        return user;
    }
}