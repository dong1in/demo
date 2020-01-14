package com.example.demo.setting.loginAndOut.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.setting.loginAndOut.entity.StudentVO;
import com.example.demo.setting.loginAndOut.mapper.StudentMapper;
import com.example.demo.setting.loginAndOut.service.ILoginAndOutService;

@Service("loginAndOutService")
public class LoginAndOutServiceImpl implements ILoginAndOutService {
    @Autowired
    StudentMapper studentMapper;

    @Override
    public StudentVO login(String account, String password) {

        return studentMapper.login(account, password);
    }

    @Override
    public List finaAll() {
        return this.studentMapper.findAll();
    }

}
