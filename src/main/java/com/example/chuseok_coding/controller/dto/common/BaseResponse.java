package com.example.chuseok_coding.controller.dto.common;

import com.example.chuseok_coding.exception.ExceptionType;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BaseResponse<T> {
    boolean success;
    @JsonInclude(Include.NON_EMPTY)
    String type;
    @JsonInclude(Include.NON_EMPTY)
    String message;
    @JsonInclude(Include.NON_NULL)
    T body;

    public static <T> BaseResponse<T> of(boolean success, String type, String message, T body) {
        return new BaseResponse<T>(success, type, message, body);
    }

    public static <T> BaseResponse<T> success(T body) {
        return new BaseResponse<T>(true, null, null, body);
    }

    public static <T> BaseResponse<T> failure(ExceptionType type) {
        return new BaseResponse<T>(false, type.getType(), type.getDesc(), null);
    }

    public static <T> BaseResponse<T> failure(ExceptionType type, T body) {
        return new BaseResponse<T>(false, type.getType(), type.getDesc(), body);
    }

    public static <T> BaseResponse<T> failure(ExceptionType type, String message) {
        return new BaseResponse<T>(false, type.getType(), type.getDesc() + message, null);
    }
}