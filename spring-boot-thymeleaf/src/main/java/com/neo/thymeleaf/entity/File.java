package com.neo.thymeleaf.entity;

import java.util.List;

/**
 * Created by jason on 2017/12/1.
 */
//将传回来的文件封装成为实体对象
public class File {
    private int id;
    private String sha;
    private String url;
    private List<Tree> tree;

    @Override
    public String toString() {
        return "File{" +
                "id=" + id +
                ", sha='" + sha + '\'' +
                ", url='" + url + '\'' +
                ", tree=" + tree +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSha() {
        return sha;
    }

    public void setSha(String sha) {
        this.sha = sha;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<Tree> getTree() {
        return tree;
    }

    public void setTree(List<Tree> tree) {
        this.tree = tree;
    }
}
