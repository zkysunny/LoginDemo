package com.neo.thymeleaf.entity;

/**
 * Created by jason on 2017/11/30.
 */
//将项目进行分装
public class Xm {

    private int id;
    private String full_name;
    private String url;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getDemo() {
        return demo;
    }

    public void setDemo(String demo) {
        this.demo = demo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String path;
    private String demo;
    private String name;

    @Override
    public String toString() {
        return "Xm{" +
                "id=" + id +
                ", full_name='" + full_name + '\'' +
                ", url='" + url + '\'' +
                ", path='" + path + '\'' +
                ", demo='" + demo + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
