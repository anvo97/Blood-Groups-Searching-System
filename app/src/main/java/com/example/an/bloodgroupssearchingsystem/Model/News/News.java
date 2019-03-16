package com.example.an.bloodgroupssearchingsystem.Model.News;

public class News {
    private String id;
    private String title,time;
    private String picture;

    public News(String id,String title, String time, String picture) {
        this.id=id;
        this.title = title;
        this.time = time;
        this.picture = picture;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }
}
