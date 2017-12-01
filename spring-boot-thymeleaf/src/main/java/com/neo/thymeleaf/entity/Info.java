package com.neo.thymeleaf.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by jason on 2017/12/1.
 */
//自己定义日志信息类
public class Info implements Serializable {

    public Info(int id, String information, Date date) {
        this.id = id;
        this.information = information;
        this.date = date;
    }

    public Info() {
    }

    private int id;
    //日志信息
    private String information;
    //插入数据库的时间
    private Date date;

    @Override
    public String toString() {
        return "Info{" +
                "id=" + id +
                ", information='" + information + '\'' +
                ", date=" + date +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
