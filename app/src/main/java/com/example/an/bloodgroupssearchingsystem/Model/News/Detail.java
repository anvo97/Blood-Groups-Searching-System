package com.example.an.bloodgroupssearchingsystem.Model.News;

public class Detail {
    private String idDetail;
    private String content;
    private String titleImage;
    private String imgDetail;

    public Detail(){
        //danh cho lay du lieu tu firebase
    }
    public Detail(String idDetail, String content, String titleImage,String imgDetail) {
        this.imgDetail=imgDetail;
        this.idDetail = idDetail;
        this.content = content;
        this.titleImage = titleImage;
    }

    public String getImgDetail() {
        return imgDetail;
    }

    public void setImgDetail(String imgDetail) {
        this.imgDetail = imgDetail;
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
        return titleImage;
    }

    public void setTitleImage(String titleImage) {
        this.titleImage = titleImage;
    }
}
