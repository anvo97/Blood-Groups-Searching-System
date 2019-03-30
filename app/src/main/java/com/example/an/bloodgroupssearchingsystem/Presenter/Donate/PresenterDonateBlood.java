package com.example.an.bloodgroupssearchingsystem.Presenter.Donate;

public interface PresenterDonateBlood {
    void ResolveRegisterDonateBlood(String name, String gender, String dateOfBirth, String phone, String email, String diachi,
                                    String ngheNghiep, String soCMND);

    void ResolveRegisterDonateBlood2(boolean daTungHienMau, boolean benhManTinh,
                                     boolean sutCan, boolean noiHach, boolean chuaRang, boolean xamMinh, boolean duocChuyenMau,
                                     boolean maTuy, boolean quanHeHIV, boolean tiemVacXin, boolean vungCoDich, boolean biCum,
                                     boolean dungThuocKhangSinh, boolean khamBacSy, boolean chatDocDaCam, boolean coThai);

    boolean checkInput(String name, String gender, String dateOfBirth, String phone, String email, String diachi,
                       String ngheNghiep, String soCMND);
}
