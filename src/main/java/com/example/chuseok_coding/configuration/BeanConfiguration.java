package com.example.chuseok_coding.configuration;

import com.example.chuseok_coding.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {
    @Bean
    public UserService userService() {
        return new UserService();
    }

}
