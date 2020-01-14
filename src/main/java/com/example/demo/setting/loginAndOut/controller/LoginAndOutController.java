package com.example.demo.setting.loginAndOut.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.setting.logMng.service.ILogMngService;
import com.example.demo.setting.loginAndOut.entity.StudentVO;
import com.example.demo.setting.loginAndOut.service.ILoginAndOutService;

@Controller
public class LoginAndOutController {
    @Autowired
    ILoginAndOutService loginAndOutService;
    @Autowired
    ILogMngService logMngService;

    @RequestMapping("/12")
    public String intoLoginPage() {
        System.out.println("=======intoLoginPage=========");
        return "login/login";
    }

    @RequestMapping("/login")
    public String login(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        String user = request.getParameter("username");
        String password = request.getParameter("password");
        StudentVO stu = loginAndOutService.login(user, password);
        if (null != stu) {
            session.setAttribute("stu", stu);
            logMngService.log(request, "用户登录", 1);
            return "index";
        } else {
            request.setAttribute("msg", "账号或密码错误");
            return "login/login";
        }
		/*if(user.equals("admin") && password.equals("123456"))
			return "index";
		return "login/login";*/

    }
}
