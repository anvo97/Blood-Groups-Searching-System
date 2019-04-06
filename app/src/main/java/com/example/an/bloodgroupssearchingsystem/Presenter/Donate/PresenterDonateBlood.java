package com.example.an.bloodgroupssearchingsystem.Presenter.Donate;

public interface PresenterDonateBlood {
    void ResolveRegisterDonateBlood(String ChuongTrinh, String Name, String Gender, String DateOfBirth, String Phone, String Email, String Diachi,
                                    String NgheNghiep, String CMND);

    void ResolveRegisterDonateBlood2(boolean DaTungHienMau, boolean BenhManTinh,
                                     boolean SutCan, boolean NoiHach, boolean ChuaRang, boolean XamMinh, boolean DuocChuyenMau,
                                     boolean MaTuy, boolean QuanHeHIV, boolean TiemVacXin, boolean VungCoDich, boolean BiCum,
                                     boolean DungThuocKhangSinh, boolean KhamBacSy, boolean ChatDocDaCam, boolean CoThai);

    boolean checkInput(String ChuongTrinh, String Name, String Gender, String DateOfBirth, String Phone, String Email, String Diachi,
                       String NgheNghiep, String CMND);
}
