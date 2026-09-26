package com.example.chuseok_coding.controller.dto;

import com.example.chuseok_coding.service.User;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

//address가 null이면 화면 출력 시 보이지 않음
@JsonInclude(Include.NON_NULL)
@Getter
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserResponseDto {
    Integer id;
    String name;
    Integer age;
    String job;
    String specialty;
    String address;
    String postcode;

    public static UserResponseDto from(User entity) {
        return new UserResponseDto(
            entity.getId(),
            entity.getName(),
            entity.getAge(),
            entity.getJob(),
            entity.getSpecialty(),
            null,
            null
        );
    }
}