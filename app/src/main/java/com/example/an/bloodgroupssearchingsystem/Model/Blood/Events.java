package com.example.an.bloodgroupssearchingsystem.Model.Blood;

public class Events {
    private String id;
    private String Name;
    private String Time;
    private String TimePost;
    private String Title;
    private String content;
    private String place;

    public Events() {
    }

    public Events(String id, String name, String time, String timePost, String title, String content, String place) {
        this.id = id;
        Name = name;
        Time = time;
        TimePost = timePost;
        Title = title;
        this.content = content;
        this.place = place;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    public String getTimePost() {
        return TimePost;
    }

    public void setTimePost(String timePost) {
        TimePost = timePost;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }
}
