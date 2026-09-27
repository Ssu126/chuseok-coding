package com.example.chuseok_coding.controller;

import com.example.chuseok_coding.controller.dto.UserCreateRequestDto;
import com.example.chuseok_coding.controller.dto.UserResponseDto;
import com.example.chuseok_coding.controller.dto.common.BaseResponse;
import com.example.chuseok_coding.exception.CustomException;
import com.example.chuseok_coding.exception.ExceptionType;
import com.example.chuseok_coding.service.IRepository;
import com.example.chuseok_coding.service.UserService;
import jakarta.validation.Valid;
import java.util.List;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
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

@Slf4j
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
            return BaseResponse.success(user);
        } catch (CustomException e) {
            //뒤에 e를 붙여줘야 상세한 경로가 출력됨
            log.warn(e.getMessage(), e);
            return BaseResponse.failure(e.getType());
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return BaseResponse.failure(ExceptionType.UNCLASSIFIED_ERROR);
        }
    }

    @ResponseBody
    @PostMapping
    //ResponseEntity는 Http 상태 코드와 헤더를 동적으로 제어
    public BaseResponse<UserResponseDto> save(@RequestBody @Valid UserCreateRequestDto request) {
        try {
            UserResponseDto user = userService.save(request.getName(), request.getAge(),
                request.getJob(), request.getSpecialty());
            return BaseResponse.success(user);
        } catch (CustomException e) {
            log.warn(e.getMessage(), e);
            return BaseResponse.failure(e.getType());
        } catch (Exception e) {
            log.warn(e.getMessage(), e);
            return BaseResponse.failure(ExceptionType.UNCLASSIFIED_ERROR);
        }
    }
}