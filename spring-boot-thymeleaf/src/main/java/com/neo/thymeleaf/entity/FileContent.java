package com.neo.thymeleaf.entity;

/**
 * Created by jason on 2017/12/1.
 */
public class FileContent {
    private int id;
    private String sha;
    private String size;
    private String url;
    private String content;
    private String encoding;

    @Override
    public String toString() {
        return "FileContent{" +
                "id=" + id +
                ", sha='" + sha + '\'' +
                ", size='" + size + '\'' +
                ", url='" + url + '\'' +
                ", content='" + content + '\'' +
                ", encoding='" + encoding + '\'' +
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getEncoding() {
        return encoding;
    }

    public void setEncoding(String encoding) {
        this.encoding = encoding;
    }
}
