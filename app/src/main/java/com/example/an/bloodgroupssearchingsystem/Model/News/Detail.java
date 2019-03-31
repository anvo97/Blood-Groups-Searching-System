package com.example.an.bloodgroupssearchingsystem.Model.News;

public class Detail {
    private String idDetail;
    private String content;
    private String TitleImage;
    private String ImageDetail;

    public Detail(){
        //danh cho lay du lieu tu firebase
    }

    public Detail(String idDetail, String content, String titleImage, String imageDetail) {
        this.idDetail = idDetail;
        this.content = content;
        TitleImage = titleImage;
        ImageDetail = imageDetail;
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
}
