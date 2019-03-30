package com.example.an.bloodgroupssearchingsystem.Presenter.Donate;

import com.example.an.bloodgroupssearchingsystem.Model.Donate.ModelRegisterDonateBlood;
import com.example.an.bloodgroupssearchingsystem.View.Donate.DonateView;
import com.google.firebase.auth.FirebaseAuth;

public class PresenterLogicDonateBlood implements PresenterDonateBlood {
    private FirebaseAuth mAuth;
    private DonateView viewRegister;
    private ModelRegisterDonateBlood modelRegister = new ModelRegisterDonateBlood();

    public PresenterLogicDonateBlood(DonateView viewRegister) {
        this.viewRegister = viewRegister;
    }

    @Override
    public void ResolveRegisterDonateBlood(String name, String gender, String dateOfBirth, String phone, String email, String diachi, String ngheNghiep, String soCMND) {
        modelRegister.setDataDonateBlood(name, gender, dateOfBirth, phone, email, diachi, ngheNghiep, soCMND);
    }

    @Override
    public void ResolveRegisterDonateBlood2(boolean daTungHienMau, boolean benhManTinh, boolean sutCan, boolean noiHach, boolean chuaRang, boolean xamMinh, boolean duocChuyenMau, boolean maTuy, boolean quanHeHIV, boolean tiemVacXin, boolean vungCoDich, boolean biCum, boolean dungThuocKhangSinh, boolean khamBacSy, boolean chatDocDaCam, boolean coThai) {
        modelRegister.setDataDonateBlood2(daTungHienMau, benhManTinh, sutCan,
                noiHach, chuaRang, xamMinh, duocChuyenMau, maTuy, quanHeHIV, tiemVacXin, vungCoDich, biCum, dungThuocKhangSinh, khamBacSy,
                chatDocDaCam, coThai);
    }

    @Override
    public boolean checkInput(String name, String gender, String dateOfBirth, String phone, String email, String diachi, String ngheNghiep, String soCMND) {
        if (name.equals("") || dateOfBirth.equals("") || phone.equals("") || email.equals("") || diachi.equals("") || ngheNghiep.equals("") || soCMND.equals("")) {
            viewRegister.checkInput();
            return false;
        } else {
            return true;
        }
    }
}
