package com.example.chuseok_coding.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CustomErrorController {
    @RequestMapping(value = "/error", method = RequestMethod.GET)
    //HttpServletRequest는 클라이언트로부터 받은 요청을 읽기 위해 제공하는 저수준 인터페이스
    //String은 이름만, ModelAndView는 이름과 데이터를 객체에 담아 반환
    public ModelAndView handleError(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        //클라이언트가 아닌 서버 내부에서 공유한 에러 상태 코드
        //getAttribute는 서버 내부의 컴포넌트끼리 서로 데이터를 공유할 수 있는 임시 저장소에서 값을 꺼내는 메서드
        //RequestDispatcher는 백엔드의 다른 자원으로 가로채서 전달하는 역할
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        if (status != null) {
            Integer statusCode = Integer.valueOf(status.toString());
            if (statusCode == HttpStatus.NOT_FOUND.value()) {
                modelAndView.addObject("status", statusCode);
                //getReasonPhrase는 설명 문구 "NOt Found"를 가져온다.
                modelAndView.addObject("reason", HttpStatus.NOT_FOUND.getReasonPhrase());
            } else if (statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
                modelAndView.addObject("status", statusCode);
                modelAndView.addObject("reason", HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
            }
        }
        //error.html로 지정
        modelAndView.setViewName("error");
        return modelAndView;
    }
}