package com.example.chuseok_coding.controller.dto;


import com.example.chuseok_coding.exception.CustomException;
import com.example.chuseok_coding.exception.ExceptionType;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonCreator.Mode;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import java.util.NoSuchElementException;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.util.Arrays;
import java.util.List;

@Getter
@AllArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public enum JobType {
    DEVELOPER("Developer", Arrays.asList("Frontend", "Backend")),
    ENGINEER("Engineer", Arrays.asList("DevOps", "SRE"));

    String name;
    List<String> titles;

    //json 라이브러리에 주입 통로 선언, 텍스트 문자열 하나만 들어가는 모드 적용
    //예시: {"name" : "Developer" } -> "Developer"
    @JsonCreator(mode = Mode.DELEGATING)
    public static JobType deserialize(String name) {
        for (JobType each : JobType.values()) {
            if (each.getName().equals(name)) {
                return each;
            }
        }
        throw new CustomException(ExceptionType.INVALID_INPUT, "JobType 내 해당하는 Enum 이 존재하지 않습니다. name : " + name);
    }
}