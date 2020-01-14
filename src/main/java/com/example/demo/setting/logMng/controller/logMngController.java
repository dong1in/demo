package com.example.demo.setting.logMng.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.conmon.util.Page;
import com.example.demo.setting.logMng.entity.LogVO;
import com.example.demo.setting.logMng.service.ILogMngService;
import com.example.demo.setting.loginAndOut.service.ILoginAndOutService;
import com.google.gson.Gson;
import com.google.gson.JsonArray;

import cn.edu.hunnu.gxy.sys.settings.loginAndOut.entity.StudentVO;
import net.sf.json.JSONArray;

@RequestMapping("/log/*")
@Controller
public class logMngController {
    @Autowired
    ILogMngService logMngService;
    @Autowired
    ILoginAndOutService loginAndOutService;

    @RequestMapping(value = "/intoLogPage", produces = "intoLogPage/html;charset=UTF-8")
    public String intoLogPage(HttpServletRequest request, HttpServletResponse response) {

        logMngService.log(request, "跳转到日志界面", 1);
        List<StudentVO> stus = this.loginAndOutService.finaAll();
        request.setAttribute("stus", stus);
        return "dd/logMng";
    }

    @RequestMapping(value = "/getLogList", produces = "getLogList/html;charset=UTF-8")
    public void getLogList(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("----------didi-----------");
        response.setContentType("text/html;charset=utf-8");
        List<LogVO> list = null;
        list = logMngService.findAll();
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("code", 0);
        result.put("msg", "");
        result.put("count", list.size());

        JSONArray array = JSONArray.fromObject(list);
        result.put("data", array);

        Gson gson = new Gson();
        String data = gson.toJson(result);

        PrintWriter out = null;
        try {
            out = response.getWriter();
            out.write(data);
        } catch (IOException e) {
            e.printStackTrace();
            logMngService.log(request, "取日志数据", 0);
        } finally {
            out.close();
        }
        logMngService.log(request, "取日志数据", 1);
    }

    @RequestMapping("/selectByPage")
    public void selectByPage(HttpServletRequest request, HttpServletResponse response, Integer limit, Integer page,
                             Integer opId, String content) {
        StringBuffer where = new StringBuffer("1 = 1");

        if (null != opId) {
            where.append(" and opId= " + opId);
        }

        if (null != content && content.trim().length() > 0) {
            where.append(" and content like '%" + content.trim() + "%' ");
        }
        Page resultPage = this.logMngService.selectByPage(where.toString(), limit, page);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("code", 0);
        result.put("msg", "");
        result.put("count", resultPage.rowCount);
        JSONArray array = JSONArray.fromObject(resultPage.data);
        result.put("data", array);
        Gson gson = new Gson();
        String data = gson.toJson(result);
        PrintWriter out = null;
        try {
            response.setContentType("text/html;charset=utf-8");
            out = response.getWriter();
            out.write(data);
        } catch (IOException e) {
            e.printStackTrace();
            logMngService.log(request, "取日志数据", 0);
        } finally {
            out.close();
        }
        logMngService.log(request, "取日志数据", 1);
    }

}
