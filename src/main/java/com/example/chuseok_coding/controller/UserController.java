package com.example.chuseok_coding.controller;

import com.example.chuseok_coding.controller.dto.UserCreateRequestDto;
import com.example.chuseok_coding.controller.dto.UserResponseDto;
import com.example.chuseok_coding.controller.dto.common.BaseResponse;
import com.example.chuseok_coding.service.IRepository;
import com.example.chuseok_coding.service.UserService;
import jakarta.validation.Valid;
import java.util.List;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class UserController {
    UserService userService;

    //Bean 객체들을 생성, 조립, 보관하는 컨테이너
    @Autowired
    private ApplicationContext applicationContext;

    @ResponseBody
    @GetMapping("/bean")
    public String bean() {
        return applicationContext.getBean(IRepository.class).toString();
    }

    @GetMapping
    public String userPage(Model model) {
        List<UserResponseDto> users = userService.findAll();
        model.addAttribute("users", users);
        return "/users/list";
    }

    //users/detail?id=1
    @GetMapping(value = "/detail")
    public String detailPage(@RequestParam Integer id, Model model) {
        UserResponseDto user = userService.findById(id);
        model.addAttribute("id", user.getId());
        model.addAttribute("name", user.getName());
        model.addAttribute("age", user.getAge());
        model.addAttribute("job", user.getJob());
        model.addAttribute("specialty", user.getSpecialty());
        return "/users/detail";
    }

    @ResponseBody
    @GetMapping(value = "/data")
    public BaseResponse<UserResponseDto> detailData(@RequestParam Integer id) {
        try {
            UserResponseDto user = userService.findById(id);
            return BaseResponse.of(true, null, null, user);
        //CustomException으로 수정하지 않음
        } catch (RuntimeException e) {
            return BaseResponse.of(false, HttpStatus.INTERNAL_SERVER_ERROR.name(), e.getMessage(), null);
        } catch (Exception e) {
            return BaseResponse.of(false, "Z10", "내부에서 에러가 발생했습니다. 추가 메시지 : " + HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }

    @ResponseBody
    @PostMapping
    //ResponseEntity는 Http 상태 코드와 헤더를 동적으로 제어
    public BaseResponse<UserResponseDto> save(@RequestBody @Valid UserCreateRequestDto request) {
        try {
            UserResponseDto user = userService.save(request.getName(), request.getAge(),
                request.getJob(), request.getSpecialty());
            return BaseResponse.of(true, null, null, user);
        } catch (RuntimeException e) {
            return BaseResponse.of(false, HttpStatus.INTERNAL_SERVER_ERROR.name(), e.getMessage(),
                null);
        } catch (Exception e) {
            return BaseResponse.of(false, "Z10",
                "내부에서 에러가 발생했습니다. 추가 메시지 : " + HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }
}