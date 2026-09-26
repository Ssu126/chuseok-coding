package com.example.chuseok_coding.controller;

import com.example.chuseok_coding.service.User;
import com.example.chuseok_coding.service.UserServiceInterface;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserServiceInterface userService;

    @GetMapping
    public ModelAndView userPage() {
        ModelAndView modelAndView = new ModelAndView();
        List<User> users = userService.findAll();
        modelAndView.addObject("users", users);
        modelAndView.setViewName("/users/list");
        return modelAndView;
    }

    @GetMapping(value = "/1/detail")
    public ModelAndView detailPage(Model model) {
        ModelAndView modelAndView = new ModelAndView();
        User user = userService.findById(1);
        modelAndView.addObject("id", user.getId());
        modelAndView.addObject("name", user.getName());
        modelAndView.addObject("age", user.getAge());
        modelAndView.addObject("job", user.getJob());
        modelAndView.addObject("specialty", user.getSpecialty());
        modelAndView.setViewName("/users/detail");
        return modelAndView;
    }
}