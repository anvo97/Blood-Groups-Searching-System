package com.example.an.bloodgroupssearchingsystem.Model.Donate;

public class PeopleDonateBlood {

    private String ChuongTrinh;
    private String Name;
    private String Gender;
    private String DateOfBirth;
    private String Phone;
    private String Email;
    private String Diachi;
    private String NgheNghiep;
    private String CMND;

    public PeopleDonateBlood(String ChuongTrinh, String Name, String Gender, String DateOfBirth, String Phone, String Email, String Diachi,
                             String NgheNghiep, String CMND) {
        this.ChuongTrinh = ChuongTrinh;
        this.Name = Name;
        this.Gender = Gender;
        this.DateOfBirth = DateOfBirth;
        this.Phone = Phone;
        this.Email = Email;
        this.Diachi = Diachi;
        this.NgheNghiep = NgheNghiep;
        this.CMND = CMND;
    }

    public String getChuongTrinh() {
        return ChuongTrinh;
    }

    public void setChuongTrinh(String ChuongTrinh) {
        this.ChuongTrinh = ChuongTrinh;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String Gender) {
        this.Gender = Gender;
    }

    public String getDateOfBirth() {
        return DateOfBirth;
    }

    public void setDateOfBirth(String DateOfBirth) {
        this.DateOfBirth = DateOfBirth;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String Phone) {
        this.Phone = Phone;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getDiachi() {
        return Diachi;
    }

    public void setDiachi(String Diachi) {
        this.Diachi = Diachi;
    }

    public String getNgheNghiep() {
        return NgheNghiep;
    }

    public void setNgheNghiep(String NgheNghiep) {
        this.NgheNghiep = NgheNghiep;
    }

    public String getCMND() {
        return CMND;
    }

    public void setCMND(String CMND) {
        this.CMND = CMND;
    }
}
