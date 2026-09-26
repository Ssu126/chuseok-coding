package com.example.chuseok_coding.controller.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserCreateRequestDto {
    String name;
    Integer age;
    String job;
    String specialty = "(empty)";
}
