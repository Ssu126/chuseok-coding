package com.example.chuseok_coding.controller;

import com.example.chuseok_coding.service.User;
import com.example.chuseok_coding.service.UserServiceInterface;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @GetMapping(value = "/{id}/detail")
    public String detailPage(@PathVariable Integer id, Model model) {
        User user = AUserService.findById(id);
        model.addAttribute("id", user.getId());
        model.addAttribute("name", user.getName());
        model.addAttribute("age", user.getAge());
        model.addAttribute("job", user.getJob());
        model.addAttribute("specialty", user.getSpecialty());
        return "/users/detail";
    }

    @ResponseBody
    @GetMapping(value = "/{id}/data")
    public User detailData(@PathVariable Integer id) {
        User user = AUserService.findById(id);
        return user;
    }
}