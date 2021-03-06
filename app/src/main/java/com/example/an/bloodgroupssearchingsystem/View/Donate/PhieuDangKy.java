package com.example.an.bloodgroupssearchingsystem.View.Donate;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.bigkoo.svprogresshud.SVProgressHUD;
import com.example.an.bloodgroupssearchingsystem.Presenter.Donate.PresenterLogicDonateBlood;
import com.example.an.bloodgroupssearchingsystem.R;
import com.example.an.bloodgroupssearchingsystem.View.Login.MainActivity;
import com.example.an.bloodgroupssearchingsystem.View.Menu.MenuActivity;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class PhieuDangKy extends AppCompatActivity implements DonateView, View.OnClickListener {

    private PresenterLogicDonateBlood presenterLogicDonateBlood = new PresenterLogicDonateBlood(this);

    private EditText edt_MaDK;
    private RadioGroup radioGroup1, radioGroup2, radioGroup3, radioGroup4, radioGroup5, radioGroup6, radioGroup7,
            radioGroup8, radioGroup9, radioGroup10, radioGroup11, radioGroup12, radioGroup13, radioGroup14,
            radioGroup15, radioGroup16;
    private RadioButton radioCo1, radioCo2, radioCo3, radioCo4, radioCo5, radioCo6, radioCo7, radioCo8, radioCo9,
            radioCo10, radioCo11, radioCo12, radioCo13, radioCo14, radioCo15, radioCo16, radioKhong1, radioKhong2,
            radioKhong3, radioKhong4, radioKhong5, radioKhong6, radioKhong7, radioKhong8, radioKhong9, radioKhong10,
            radioKhong11, radioKhong12, radioKhong13, radioKhong14, radioKhong15, radioKhong16;
    private Button btnPhieuDangKy, btnHuy;
    private boolean DaTungHienMau, BenhManTinh, SutCan, NoiHach, ChuaRang, XamMinh, DuocChuyenMau, MaTuy,
            QuanHeHIV, TiemVacXin, VungCoDich, BiCum, DungThuocKhangSinh, KhamBacSy, ChatDocDaCam, CoThai;
    private DatabaseReference mData;
    private SVProgressHUD mSvProgressHUD;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.phieu_dang_ky);
        anhXa();
        dialogCungCap();
        mSvProgressHUD.dismiss();
        setID();
        start();
    }

    private void anhXa() {
        edt_MaDK = (EditText) findViewById(R.id.edt_MaDK);
        radioGroup1 = (RadioGroup) findViewById(R.id.radio_Group_1);
        radioGroup2 = (RadioGroup) findViewById(R.id.radio_Group_2);
        radioGroup3 = (RadioGroup) findViewById(R.id.radio_Group_3);
        radioGroup4 = (RadioGroup) findViewById(R.id.radio_Group_4);
        radioGroup5 = (RadioGroup) findViewById(R.id.radio_Group_5);
        radioGroup6 = (RadioGroup) findViewById(R.id.radio_Group_6);
        radioGroup7 = (RadioGroup) findViewById(R.id.radio_Group_7);
        radioGroup8 = (RadioGroup) findViewById(R.id.radio_Group_8);
        radioGroup9 = (RadioGroup) findViewById(R.id.radio_Group_9);
        radioGroup10 = (RadioGroup) findViewById(R.id.radio_Group_10);
        radioGroup11 = (RadioGroup) findViewById(R.id.radio_Group_11);
        radioGroup12 = (RadioGroup) findViewById(R.id.radio_Group_12);
        radioGroup13 = (RadioGroup) findViewById(R.id.radio_Group_13);
        radioGroup14 = (RadioGroup) findViewById(R.id.radio_Group_14);
        radioGroup15 = (RadioGroup) findViewById(R.id.radio_Group_15);
        radioGroup16 = (RadioGroup) findViewById(R.id.radio_Group_16);
        radioCo1 = (RadioButton) findViewById(R.id.radio_Co_1);
        radioCo2 = (RadioButton) findViewById(R.id.radio_Co_2);
        radioCo3 = (RadioButton) findViewById(R.id.radio_Co_3);
        radioCo4 = (RadioButton) findViewById(R.id.radio_Co_4);
        radioCo5 = (RadioButton) findViewById(R.id.radio_Co_5);
        radioCo6 = (RadioButton) findViewById(R.id.radio_Co_6);
        radioCo7 = (RadioButton) findViewById(R.id.radio_Co_7);
        radioCo8 = (RadioButton) findViewById(R.id.radio_Co_8);
        radioCo9 = (RadioButton) findViewById(R.id.radio_Co_9);
        radioCo10 = (RadioButton) findViewById(R.id.radio_Co_10);
        radioCo11 = (RadioButton) findViewById(R.id.radio_Co_11);
        radioCo12 = (RadioButton) findViewById(R.id.radio_Co_12);
        radioCo13 = (RadioButton) findViewById(R.id.radio_Co_13);
        radioCo14 = (RadioButton) findViewById(R.id.radio_Co_14);
        radioCo15 = (RadioButton) findViewById(R.id.radio_Co_15);
        radioCo16 = (RadioButton) findViewById(R.id.radio_Co_16);
        radioKhong1 = (RadioButton) findViewById(R.id.radio_Khong_1);
        radioKhong2 = (RadioButton) findViewById(R.id.radio_Khong_2);
        radioKhong3 = (RadioButton) findViewById(R.id.radio_Khong_3);
        radioKhong4 = (RadioButton) findViewById(R.id.radio_Khong_4);
        radioKhong5 = (RadioButton) findViewById(R.id.radio_Khong_5);
        radioKhong6 = (RadioButton) findViewById(R.id.radio_Khong_6);
        radioKhong7 = (RadioButton) findViewById(R.id.radio_Khong_7);
        radioKhong8 = (RadioButton) findViewById(R.id.radio_Khong_8);
        radioKhong9 = (RadioButton) findViewById(R.id.radio_Khong_9);
        radioKhong10 = (RadioButton) findViewById(R.id.radio_Khong_10);
        radioKhong11 = (RadioButton) findViewById(R.id.radio_Khong_11);
        radioKhong12 = (RadioButton) findViewById(R.id.radio_Khong_12);
        radioKhong13 = (RadioButton) findViewById(R.id.radio_Khong_13);
        radioKhong14 = (RadioButton) findViewById(R.id.radio_Khong_14);
        radioKhong15 = (RadioButton) findViewById(R.id.radio_Khong_15);
        radioKhong16 = (RadioButton) findViewById(R.id.radio_Khong_16);
        btnPhieuDangKy = (Button) findViewById(R.id.btn_phieudangky);
        btnHuy = (Button) findViewById(R.id.btn_Huy);
        mData = FirebaseDatabase.getInstance().getReference();
        mSvProgressHUD = new SVProgressHUD(this);
        mSvProgressHUD.show();
    }

    public void start() {
        radioCo1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                doOnGameCharacterChanged(buttonView, isChecked);
            }
        });

        radioCo2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                doOnGameCharacterChanged(buttonView, isChecked);
            }
        });
        radioCo3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                doOnGameCharacterChanged(buttonView, isChecked);
            }
        });
        radioCo4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                doOnGameCharacterChanged(buttonView, isChecked);
            }
        });
        radioCo5.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                doOnGameCharacterChanged(buttonView, isChecked);
            }
        });
        radioCo6.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                doOnGameCharacterChanged(buttonView, isChecked);
            }
        });
        radioCo7.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                doOnGameCharacterChanged(buttonView, isChecked);
            }
        });
        radioCo8.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                doOnGameCharacterChanged(buttonView, isChecked);
            }
        });
        radioCo9.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                doOnGameCharacterChanged(buttonView, isChecked);
            }
        });
        radioCo10.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                doOnGameCharacterChanged(buttonView, isChecked);
            }
        });
        radioCo11.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                doOnGameCharacterChanged(buttonView, isChecked);
            }
        });
        radioCo12.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                doOnGameCharacterChanged(buttonView, isChecked);
            }
        });
        radioCo13.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                doOnGameCharacterChanged(buttonView, isChecked);
            }
        });
        radioCo14.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                doOnGameCharacterChanged(buttonView, isChecked);
            }
        });
        radioCo15.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                doOnGameCharacterChanged(buttonView, isChecked);
            }
        });
        radioCo16.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                doOnGameCharacterChanged(buttonView, isChecked);
            }
        });
        radioKhong1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                doOnGameCharacterChanged(buttonView, isChecked);
            }
        });
        radioKhong2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                doOnGameCharacterChanged(buttonView, isChecked);
            }
        });
        radioKhong3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                doOnGameCharacterChanged(buttonView, isChecked);
            }
        });
        radioKhong4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                doOnGameCharacterChanged(buttonView, isChecked);
            }
        });
        radioKhong5.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                doOnGameCharacterChanged(buttonView, isChecked);
            }
        });
        radioKhong6.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                doOnGameCharacterChanged(buttonView, isChecked);
            }
        });
        radioKhong7.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                doOnGameCharacterChanged(buttonView, isChecked);
            }
        });
        radioKhong8.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                doOnGameCharacterChanged(buttonView, isChecked);
            }
        });
        radioKhong9.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                doOnGameCharacterChanged(buttonView, isChecked);
            }
        });
        radioKhong10.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                doOnGameCharacterChanged(buttonView, isChecked);
            }
        });
        radioKhong11.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                doOnGameCharacterChanged(buttonView, isChecked);
            }
        });
        radioKhong12.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                doOnGameCharacterChanged(buttonView, isChecked);
            }
        });
        radioKhong13.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                doOnGameCharacterChanged(buttonView, isChecked);
            }
        });
        radioKhong14.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                doOnGameCharacterChanged(buttonView, isChecked);
            }
        });
        radioKhong15.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                doOnGameCharacterChanged(buttonView, isChecked);
            }
        });
        radioKhong16.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                doOnGameCharacterChanged(buttonView, isChecked);
            }
        });
        btnPhieuDangKy.setOnClickListener(this);
        btnHuy.setOnClickListener(this);
    }

    private void doOnGameCharacterChanged(CompoundButton buttonView, boolean isChecked) {
        RadioButton radio = (RadioButton) buttonView;
    }

    public void onClick(View v) {
        if (v == btnPhieuDangKy) {
            mSvProgressHUD.show();
            if (radioCo1.isChecked()) {
                DaTungHienMau = true;
            } else {
                DaTungHienMau = false;
            }
            if (radioCo2.isChecked()) {
                BenhManTinh = true;
            } else {
                BenhManTinh = false;
            }
            if (radioCo3.isChecked()) {
                SutCan = true;
            } else {
                SutCan = false;
            }
            if (radioCo4.isChecked()) {
                NoiHach = true;
            } else {
                NoiHach = false;
            }
            if (radioCo5.isChecked()) {
                ChuaRang = true;
            } else {
                ChuaRang = false;
            }
            if (radioCo6.isChecked()) {
                XamMinh = true;
            } else {
                XamMinh = false;
            }
            if (radioCo7.isChecked()) {
                DuocChuyenMau = true;
            } else {
                DuocChuyenMau = false;
            }
            if (radioCo8.isChecked()) {
                MaTuy = true;
            } else {
                MaTuy = false;
            }
            if (radioCo9.isChecked()) {
                QuanHeHIV = true;
            } else {
                QuanHeHIV = false;
            }
            if (radioCo10.isChecked()) {
                TiemVacXin = true;
            } else {
                TiemVacXin = false;
            }
            if (radioCo11.isChecked()) {
                VungCoDich = true;
            } else {
                VungCoDich = false;
            }
            if (radioCo12.isChecked()) {
                BiCum = true;
            } else {
                BiCum = false;
            }
            if (radioCo13.isChecked()) {
                DungThuocKhangSinh = true;
            } else {
                DungThuocKhangSinh = false;
            }
            if (radioCo14.isChecked()) {
                KhamBacSy = true;
            } else {
                KhamBacSy = false;
            }
            if (radioCo15.isChecked()) {
                ChatDocDaCam = true;
            } else {
                ChatDocDaCam = false;
            }
            if (radioCo16.isChecked()) {
                CoThai = true;
            } else {
                CoThai = false;
            }
            if (MaTuy || QuanHeHIV || (CoThai && VungCoDich)) {
                dialogKhongDuDieuKien();
            } else {
                presenterLogicDonateBlood.ResolveRegisterDonateBlood2(DaTungHienMau, BenhManTinh, SutCan, NoiHach, ChuaRang, XamMinh, DuocChuyenMau, MaTuy,
                        QuanHeHIV, TiemVacXin, VungCoDich, BiCum, DungThuocKhangSinh, KhamBacSy, ChatDocDaCam, CoThai);
                dialogKetQua();
                mSvProgressHUD.dismiss();
            }
        }
        if (v == btnHuy) {
            mSvProgressHUD.show();
            removeID();
            startActivity(new Intent(this, MenuActivity.class));
            finish();
        }
    }

    @Override
    public void checkInput() {
        Toast.makeText(this, "Không được để trống thông tin", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void successfully() {
        Toast.makeText(this, "Đăng ký thành công", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void unsuccessfully() {
        Toast.makeText(this, "Đăng ký thất bại", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    public void dialogKhongDuDieuKien() {
        new AlertDialog.Builder(PhieuDangKy.this)
                .setTitle("Thông báo")
                .setMessage("Bạn không đủ điều kiện để hiến máu, cảm ơn bạn đã dành thời gian quan tâm đến ứng dụng.")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        removeID();
                        startActivity(new Intent(PhieuDangKy.this, MenuActivity.class));
                        finish();
                    }
                })
                .setCancelable(false)
                .show();
    }

    public void dialogCungCap() {
        new AlertDialog.Builder(PhieuDangKy.this)
                .setTitle("Thông báo")
                .setMessage("Mời bạn cung cấp thêm thông tin(nếu có).")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setCancelable(false)
                .show();
    }

    public void dialogKetQua() {
        new AlertDialog.Builder(PhieuDangKy.this)
                .setTitle("Đăng ký thành công")
                .setMessage("Cảm ơn bạn đã đăng ký. Vui lòng chờ phản hồi của chúng tôi qua gmail.")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mSvProgressHUD.show();
                        startActivity(new Intent(PhieuDangKy.this, MenuActivity.class));
                        mSvProgressHUD.dismiss();
                        finish();
                    }
                })
                .setCancelable(false)
                .show();
    }

    public void setID() {
        mData.child("InformationDonateBlood").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                edt_MaDK.setText(dataSnapshot.getKey());
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void removeID() {
        mData.child("InformationDonateBlood").child(edt_MaDK.getText().toString()).removeValue();
    }
}
