package com.example.demo.setting.logMng.service.impl;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.conmon.util.Page;
import com.example.demo.setting.logMng.entity.LogVO;
import com.example.demo.setting.logMng.mapper.LogMapper;
import com.example.demo.setting.logMng.service.ILogMngService;
import com.example.demo.setting.loginAndOut.entity.StudentVO;

@Service("logMngService")
public class LogMngServiceImpl implements ILogMngService {

    @Autowired
    LogMapper logMapper;

    @Override
    public boolean log(HttpServletRequest request, String content, int result) {
        try {
            HttpSession session = request.getSession();
            StudentVO stu = (StudentVO) session.getAttribute("stu");
            if (null != stu) {
                LogVO lv = new LogVO();
                lv.setOpId(stu.getId());
                lv.setOpTime(new Date());
                lv.setIP(getIpAddress(request));
                lv.setResult(result);
                lv.setContent(content);
                logMapper.insert(lv);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public List<LogVO> findAll() {

        return logMapper.findAll();
    }

    @Override
    public Page selectByPage(String where, Integer limit, Integer page) {
        Integer rowCount = this.logMapper.Count(where);
        Page result = new Page(limit, page, rowCount);
        result.data = this.logMapper.seleceByPage(where, result.start, limit);
        return result;
    }

    public static String getIpAddress(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

}
