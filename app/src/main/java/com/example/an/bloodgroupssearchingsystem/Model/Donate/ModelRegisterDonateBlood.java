package com.example.an.bloodgroupssearchingsystem.Model.Donate;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ModelRegisterDonateBlood {
    private DatabaseReference mData;
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();


    public void setDataDonateBlood(String name, String gender, String dateOfBirth, String phone, String email, String diachi,
                                   String ngheNghiep, String soCMND) {
        mData = FirebaseDatabase.getInstance().getReference();
        PeopleDonateBlood formReister = new PeopleDonateBlood(name, gender, dateOfBirth, phone, email, diachi, ngheNghiep, soCMND);

        mData.child("InformationDonateBlood").child(user.getUid()).child("FormRegister").setValue(formReister);
    }

    public void setDataDonateBlood2(boolean daTungHienMau, boolean benhManTinh,
                                    boolean sutCan, boolean noiHach, boolean chuaRang, boolean xamMinh, boolean duocChuyenMau,
                                    boolean maTuy, boolean quanHeHIV, boolean tiemVacXin, boolean vungCoDich, boolean biCum,
                                    boolean dungThuocKhangSinh, boolean khamBacSy, boolean chatDocDaCam, boolean coThai) {
        mData = FirebaseDatabase.getInstance().getReference();

        PeopleInfoDonateBlood information = new PeopleInfoDonateBlood(daTungHienMau, benhManTinh, sutCan, noiHach, chuaRang, xamMinh, duocChuyenMau, maTuy,
                quanHeHIV, tiemVacXin, vungCoDich, biCum, dungThuocKhangSinh, khamBacSy, chatDocDaCam, coThai);

        mData.child("InformationDonateBlood").child(user.getUid()).child("InformationPeopleDonateBlood").setValue(information);
    }

}
