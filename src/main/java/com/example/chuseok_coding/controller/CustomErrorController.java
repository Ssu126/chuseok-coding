package com.example.chuseok_coding.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class CustomErrorController {
    @RequestMapping(value = "/error", method = RequestMethod.GET)
    //HttpServletRequest는 클라이언트로부터 받은 요청을 읽기 위해 제공하는 저수준 인터페이스
    public String handleError(HttpServletRequest request) {
        //클라이언트가 아닌 서버 내부에서 공유한 에러 상태 코드
        //getAttribute는 서버 내부의 컴포넌트끼리 서로 데이터를 공유할 수 있는 임시 저장소에서 값을 꺼내는 메서드
        //RequestDispatcher는 백엔드의 다른 자원으로 가로채서 전달하는 역할
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        if (status != null) {
            Integer statusCode = Integer.valueOf(status.toString());
            if (statusCode == HttpStatus.NOT_FOUND.value()) {
                return "error/404";
            } else if (statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
                return "error/500";
            }
        }
        return "error";
    }
}