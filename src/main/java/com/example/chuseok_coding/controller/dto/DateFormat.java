package com.example.chuseok_coding.controller.dto;

import com.fasterxml.jackson.annotation.JacksonAnnotationsInside;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

//Json 설정들을 메타 어노테이션으로 취급
@JacksonAnnotationsInside
//실행되는 동안에도 메모리에 유지
@Retention(RetentionPolicy.RUNTIME)
//Timezone을 통해 한국 시간 기준으로 설정
@JsonFormat(shape = Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
//커스텀 어노테이션을 창조
public @interface DateFormat {
}
