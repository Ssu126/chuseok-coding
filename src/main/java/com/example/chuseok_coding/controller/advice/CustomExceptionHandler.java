package com.example.chuseok_coding.controller.advice;

import com.example.chuseok_coding.controller.dto.common.BaseResponse;
import com.example.chuseok_coding.exception.CustomException;
import com.example.chuseok_coding.exception.ExceptionType;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
//Controller 앞단에서 발생하는 에러를 캐치하기 위함
@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler
    public BaseResponse<Void> handle(CustomException e) {
        ExceptionType type = e.getType();
        //위험도 레벨, 원인, 에러 메시지를 한꺼번에 기록
        log.atLevel(type.getLevel()).setCause(e).log(e.getMessage());
        return BaseResponse.failure(type);
    }

    @ExceptionHandler
    public BaseResponse<List<FieldErrorDto>> handle(MethodArgumentNotValidException e) {
        List<FieldErrorDto> errors = new ArrayList<>();
        //StringBuilder는 문자열을 자주 변경해야 할 때 사용하는 문자열 조립 전용 클래스
        StringBuilder messageBuilder = new StringBuilder();
        //ObjectError는 가장 포괄적인 에러 객체, FieldError는 특정 필드에서 터진 에러
        //getBindResult는 바인딩 결과 호출, getAllErrors는 터진 모든 에러 호출
        for (ObjectError each : e.getBindingResult().getAllErrors()) {
            FieldError eachError = (FieldError) each;
            messageBuilder.append(String.format("[%s = %s : %s] ",
                eachError.getField(), eachError.getRejectedValue(), eachError.getDefaultMessage()));
            errors.add(new FieldErrorDto(eachError.getField(), eachError.getRejectedValue(),
                eachError.getDefaultMessage()));
        }
        log.warn(messageBuilder.toString(), e);
        return BaseResponse.failure(ExceptionType.INVALID_INPUT, errors);
    }

    @ExceptionHandler
    public BaseResponse<Void> handle(Exception e) {
        log.error(e.getMessage(), e);
        return BaseResponse.failure(ExceptionType.UNCLASSIFIED_ERROR);
    }
}