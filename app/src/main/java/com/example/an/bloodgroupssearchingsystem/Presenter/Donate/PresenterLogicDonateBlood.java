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
    public void ResolveRegisterDonateBlood(String ChuongTrinh, String Name, String Gender, String DateOfBirth, String Phone, String Email, String Diachi,
                                           String NgheNghiep, String CMND) {
        modelRegister.setDataDonateBlood(ChuongTrinh, Name, Gender, DateOfBirth, Phone, Email, Diachi, NgheNghiep, CMND);
    }

    @Override
    public void ResolveRegisterDonateBlood2(boolean DaTungHienMau, boolean BenhManTinh,
                                            boolean SutCan, boolean NoiHach, boolean ChuaRang, boolean XamMinh, boolean DuocChuyenMau,
                                            boolean MaTuy, boolean QuanHeHIV, boolean TiemVacXin, boolean VungCoDich, boolean BiCum,
                                            boolean DungThuocKhangSinh, boolean KhamBacSy, boolean ChatDocDaCam, boolean CoThai) {
        modelRegister.setDataDonateBlood2(DaTungHienMau, BenhManTinh, SutCan, NoiHach, ChuaRang, XamMinh, DuocChuyenMau, MaTuy,
                QuanHeHIV, TiemVacXin, VungCoDich, BiCum, DungThuocKhangSinh, KhamBacSy, ChatDocDaCam, CoThai);
    }

    @Override
    public boolean checkInput(String ChuongTrinh, String Name, String Gender, String DateOfBirth, String Phone, String Email, String Diachi,
                              String NgheNghiep, String CMND) {
        if (Name.equals("") || DateOfBirth.equals("") || Phone.equals("") || Email.equals("") || Diachi.equals("") || NgheNghiep.equals("") || CMND.equals("")) {
            viewRegister.checkInput();
            return false;
        } else {
            return true;
        }
    }
}
