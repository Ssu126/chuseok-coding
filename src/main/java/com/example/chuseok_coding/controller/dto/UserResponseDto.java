package com.example.chuseok_coding.controller.dto;

import com.example.chuseok_coding.service.User;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

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