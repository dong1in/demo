package com.example.demo.setting.logMng.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.example.demo.conmon.util.Page;
import com.example.demo.setting.logMng.entity.LogVO;

public interface ILogMngService {

    boolean log(HttpServletRequest request, String content, int result);

    List<LogVO> findAll();

    Page selectByPage(String where, Integer limit, Integer page);
}
