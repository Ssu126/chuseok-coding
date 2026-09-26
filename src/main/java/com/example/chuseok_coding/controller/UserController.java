package com.example.chuseok_coding.controller;

import com.example.chuseok_coding.controller.dto.UserCreateRequestDto;
import com.example.chuseok_coding.service.User;
import com.example.chuseok_coding.service.UserServiceInterface;
import java.util.List;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class UserController {
    UserServiceInterface userService;

    @GetMapping
    public String userPage(Model model) {
        List<User> users = userService.findAll();
        model.addAttribute("users", users);
        return "/users/list";
    }

    //users/detail?id=1
    @GetMapping(value = "/detail")
    public String detailPage(@RequestParam Integer id, Model model) {
        User user = userService.findById(id);
        model.addAttribute("id", user.getId());
        model.addAttribute("name", user.getName());
        model.addAttribute("age", user.getAge());
        model.addAttribute("job", user.getJob());
        model.addAttribute("specialty", user.getSpecialty());
        return "/users/detail";
    }

    @ResponseBody
    @GetMapping(value = "/data")
    public User detailData(@RequestParam Integer id) {
        User user = userService.findById(id);
        return user;
    }

    @ResponseBody
    @PostMapping()
    //ModelAttribute는 Setter/생성자로 값을 주입하지만, RequestBody는 JSON을 파라싱하여 주입
    public User save(@RequestBody UserCreateRequestDto request) {
        User user = userService.save(request.getName(), request.getAge(), request.getJob(), request.getSpecialty());
        return user;
    }
}