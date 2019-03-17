package com.example.an.bloodgroupssearchingsystem.Model.Register;

public class Customer {
    private String idCustomer;
    private String Address;
    private String Avatar;
    private String Fullname;
    private String birthday;
    private String gender;
    private String phoneNumber;

    public Customer(){
        //danh cho lay du lieu tu firebase
    }

    public Customer(String address, String avatar, String fullname, String birthday, String gender, String phoneNumber) {
        Address = address;
        Avatar = avatar;
        Fullname = fullname;
        this.birthday = birthday;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
    }

    public Customer(String idCustomer, String address, String avatar, String fullname, String birthday, String gender, String phoneNumber) {
        this.idCustomer = idCustomer;
        Address = address;
        Avatar = avatar;
        Fullname = fullname;
        this.birthday = birthday;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
    }

    public String getIdCustomer() {
        return idCustomer;
    }

    public void setIdCustomer(String idCustomer) {
        this.idCustomer = idCustomer;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getAvatar() {
        return Avatar;
    }

    public void setAvatar(String avatar) {
        Avatar = avatar;
    }

    public String getFullname() {
        return Fullname;
    }

    public void setFullname(String fullname) {
        Fullname = fullname;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
