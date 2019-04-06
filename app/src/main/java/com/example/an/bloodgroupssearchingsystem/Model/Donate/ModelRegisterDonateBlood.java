package com.example.an.bloodgroupssearchingsystem.Model.Donate;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ModelRegisterDonateBlood {
    private DatabaseReference mData;
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();


    public void setDataDonateBlood(String ChuongTrinh, String Name, String Gender, String DateOfBirth, String Phone, String Email, String Diachi,
                                   String NgheNghiep, String CMND) {
        mData = FirebaseDatabase.getInstance().getReference();
        PeopleDonateBlood formReister = new PeopleDonateBlood(ChuongTrinh, Name, Gender, DateOfBirth, Phone, Email, Diachi, NgheNghiep, CMND);

        mData.child("InformationDonateBlood").child(user.getUid()).child("FormRegister").setValue(formReister);
    }

    public void setDataDonateBlood2(boolean DaTungHienMau, boolean BenhManTinh,
                                    boolean SutCan, boolean NoiHach, boolean ChuaRang, boolean XamMinh, boolean DuocChuyenMau,
                                    boolean MaTuy, boolean QuanHeHIV, boolean TiemVacXin, boolean VungCoDich, boolean BiCum,
                                    boolean DungThuocKhangSinh, boolean KhamBacSy, boolean ChatDocDaCam, boolean CoThai) {
        mData = FirebaseDatabase.getInstance().getReference();

        PeopleInfoDonateBlood information = new PeopleInfoDonateBlood(DaTungHienMau, BenhManTinh, SutCan, NoiHach, ChuaRang, XamMinh, DuocChuyenMau, MaTuy,
                QuanHeHIV, TiemVacXin, VungCoDich, BiCum, DungThuocKhangSinh, KhamBacSy, ChatDocDaCam, CoThai);

        mData.child("InformationDonateBlood").child(user.getUid()).child("InformationPeopleDonateBlood").setValue(information);
    }

}
