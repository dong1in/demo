package com.example.demo.setting.loginAndOut.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.example.demo.setting.loginAndOut.entity.StudentVO;

public interface StudentMapper {
    @Select("SELECT * FROM student WHERE account=#{account} and password=#{password}")
    StudentVO login(@Param("account") String account, @Param("password") String password);

    @Select("select * from student")
    List<StudentVO> findAll();
}
