package com.example.chuseok_coding.controller.dto;

import com.example.chuseok_coding.service.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

//address가 null이면 화면 출력 시 보이지 않음
@JsonInclude(Include.NON_NULL)
//Json 반환 시 프로퍼티 노출 순서 결정
@JsonPropertyOrder({"userId", "username"})
@Getter
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserResponseDto {
    //Json 반환 시 자바 변수 이름과 JSON의 Key 이름을 다르게 매용
    @JsonProperty("userId")
    Integer id;
    //Json 반환 시 특정 프로퍼티 제환
    @JsonIgnore
    String name;
    Integer age;
    JobType job;
    String specialty;
    @DateFormat
    LocalDateTime createdAt;
    String address;
    String postcode;

    public static UserResponseDto from(User entity) {
        return new UserResponseDto(
            entity.getId(),
            entity.getName(),
            entity.getAge(),
            entity.getJob(),
            entity.getSpecialty(),
            entity.getCreatedAt(),
            null,
            null
        );
    }
}