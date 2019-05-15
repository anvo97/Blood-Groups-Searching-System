package com.example.an.bloodgroupssearchingsystem.Model.News;

public class News {
    private String id;
    private String Title, Time;
    private String Image;
    private String classify;
    private String idDetail;
    private String content;
    private String TitleImage;
    private String ImageDetail;
    private String localhost;

    public News() {
    }

    public News(String id, String title, String time, String image, String classify, String idDetail, String content, String titleImage, String imageDetail, String localhost) {
        this.id = id;
        Title = title;
        Time = time;
        Image = image;
        this.classify = classify;
        this.idDetail = idDetail;
        this.content = content;
        TitleImage = titleImage;
        ImageDetail = imageDetail;
        this.localhost = localhost;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public String getClassify() {
        return classify;
    }

    public void setClassify(String classify) {
        this.classify = classify;
    }

    public String getIdDetail() {
        return idDetail;
    }

    public void setIdDetail(String idDetail) {
        this.idDetail = idDetail;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitleImage() {
        return TitleImage;
    }

    public void setTitleImage(String titleImage) {
        TitleImage = titleImage;
    }

    public String getImageDetail() {
        return ImageDetail;
    }

    public void setImageDetail(String imageDetail) {
        ImageDetail = imageDetail;
    }

    public String getLocalhost() {
        return localhost;
    }

    public void setLocalhost(String localhost) {
        this.localhost = localhost;
    }
}
