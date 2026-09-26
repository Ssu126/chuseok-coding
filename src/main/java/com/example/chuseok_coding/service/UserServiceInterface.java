package com.example.chuseok_coding.service;

import java.util.List;

public interface UserServiceInterface {
    User findById(Integer id);
    List<User> findAll();
}