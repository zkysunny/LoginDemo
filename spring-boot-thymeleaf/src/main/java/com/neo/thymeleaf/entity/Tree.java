package com.neo.thymeleaf.entity;

/**
 * Created by jason on 2017/12/1.
 */
//将传回来文件里面的Tree进行解析
public class Tree {
    private int id;
    private String path;
    private String mode;
    private String type;
    private String sha;
    private String size;
    private String url;
    private String xm;

    public String getXm() {
        return xm;
    }

    public void setXm(String xm) {
        this.xm = xm;
    }

    @Override
    public String toString() {
        return "Tree{" +
                "id=" + id +
                ", path='" + path + '\'' +
                ", mode='" + mode + '\'' +
                ", type='" + type + '\'' +
                ", sha='" + sha + '\'' +
                ", size='" + size + '\'' +
                ", url='" + url + '\'' +
                ", xm='" + xm + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSha() {
        return sha;
    }

    public void setSha(String sha) {
        this.sha = sha;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
