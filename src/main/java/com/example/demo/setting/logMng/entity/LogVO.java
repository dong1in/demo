package com.example.demo.setting.logMng.entity;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "log")
public class LogVO implements Serializable {
    @Id
    @Column(name = "id")
    private Integer id;
    @Column(name = "opId")
    private Integer opId;
    @Column(name = "opTime")
    @Transient
    private String transTime;
    private Date opTime;
    @Column(name = "IP")
    private String IP;
    @Column(name = "content")
    private String content;
    @Column
    private int result;
    @Column
    private String remarks;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOpId() {
        return opId;
    }

    public void setOpId(Integer opId) {
        this.opId = opId;
    }

    public Date getOpTime() {
        return opTime;
    }

    public void setOpTime(Date opTime) {
        this.opTime = opTime;
    }

    public String getIP() {
        return IP;
    }

    public void setIP(String iP) {
        IP = iP;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getTransTime() {
        SimpleDateFormat t = new SimpleDateFormat("yyyy-MM--dd HH:mm:ss");
        Date s = getOpTime();
        if (null != s) {
            transTime = t.format(s);
        }
        return transTime;
    }

    public void setTransTime(String transTime) {
        this.transTime = transTime;
    }


}
