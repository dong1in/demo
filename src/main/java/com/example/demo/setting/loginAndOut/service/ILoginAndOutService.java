package com.example.demo.setting.loginAndOut.service;

import java.util.List;

import com.example.demo.setting.loginAndOut.entity.StudentVO;

public interface ILoginAndOutService {
    StudentVO login(String account, String password);

    List finaAll();
}
