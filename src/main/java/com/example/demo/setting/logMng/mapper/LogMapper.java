package com.example.demo.setting.logMng.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.example.demo.setting.logMng.entity.LogVO;

public interface LogMapper {
    @Insert("insert into log  ( id ,opId, opTime , IP , content  , result  , remarks )  VALUES( #{lv.id},#{lv.opId},#{lv.opTime},#{lv.IP} , #{lv.content},#{lv.result}, #{lv.remarks})")
    void insert(@Param("lv") LogVO lv);

    @Select("select * from log")
    List<LogVO> findAll();

    @Select(" select log.*,student.`name` from log LEFT JOIN student ON log.opId=student.id" +
            " where ${where} LIMIT ${start},${offset}")
    List<Map<String, Object>> seleceByPage(@Param("where") String where, @Param("start") Integer start, @Param("offset") Integer offset);

    @Select(" SELECT COUNT(1) from log where ${where} ")
    Integer Count(@Param("where") String where);
}
