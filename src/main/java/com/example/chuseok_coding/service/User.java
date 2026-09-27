package com.example.chuseok_coding.service;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

@Getter
@AllArgsConstructor
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User {
    @Setter
    Integer id;
    //NotNull과 달리 ""(빈 문자열), " "(공백 문자열) 허용 불가
    @NotBlank
    String name;
    @Min(10)
    Integer age;
    @NotNull
    String job;
    String specialty;
    LocalDateTime createdAt;
}