package com.example.an.bloodgroupssearchingsystem.View.Donate;


import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.an.bloodgroupssearchingsystem.Presenter.Donate.PresenterLogicDonateBlood;
import com.example.an.bloodgroupssearchingsystem.R;
import com.example.an.bloodgroupssearchingsystem.View.UpdateInformation.MyProfileActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * A simple {@link Fragment} subclass.
 */
public class DonateFragment extends Fragment implements View.OnClickListener, DonateView {
    private PresenterLogicDonateBlood presenterLogicDonateBlood = new PresenterLogicDonateBlood(this);

    public DonateFragment() {
        // Required empty public constructor
    }

    private EditText edtTen, edtSDT, edtEmail, edtNgheNghiep, edtCMND, edtDiaChi;
    private TextView txtNgaySinh;
    private Button btnNgaySinh, btnDangKy;
    private RadioGroup radioGroup;
    private RadioButton radio_Nam, radio_Nu;
    private Calendar calendar;
    private boolean daTungHienMau, benhManTinh, sutCan, noiHach, chuaRang, xamMinh, duocChuyenMau,
            maTuy, quanHeHIV, tiemVacXin, vungCoDich, biCum, dungThuocKhangSinh, khamBacSy, chatDocDaCam, coThai;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_donate, container, false);
        // Inflate the layout for this fragment
        edtTen = (EditText) view.findViewById(R.id.editTen);
        edtSDT = (EditText) view.findViewById(R.id.editSoDT);
        edtEmail = (EditText) view.findViewById(R.id.editEmail);
        edtNgheNghiep = (EditText) view.findViewById(R.id.editNgheNghiep);
        edtCMND = (EditText) view.findViewById(R.id.editCMND);
        edtDiaChi = (EditText) view.findViewById(R.id.editDiaChi);
        txtNgaySinh = (TextView) view.findViewById(R.id.txt_NgaySinh);
        btnNgaySinh = (Button) view.findViewById(R.id.btn_NgaySinh);
        btnDangKy = (Button) view.findViewById(R.id.btn_dangky);
        radioGroup = (RadioGroup) view.findViewById(R.id.radioGioiTinh);
        radio_Nam = (RadioButton) view.findViewById(R.id.radioNam);
        radio_Nu = (RadioButton) view.findViewById(R.id.radioNu);

        radio_Nam.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                doOnGameCharacterChanged(buttonView, isChecked);
            }
        });

        radio_Nu.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                doOnGameCharacterChanged(buttonView, isChecked);
            }
        });

        btnNgaySinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pickDate();
            }
        });

        btnDangKy.setOnClickListener(this);
        return view;
    }

    // Khi radio button có thay đổi.
    private void doOnGameCharacterChanged(CompoundButton buttonView, boolean isChecked) {
        RadioButton radio = (RadioButton) buttonView;
    }

    // Khi button "Dang Ky" bị nhấn.
//    private void doDangKy()  {
//        int gameCharacter = this.radioGroup.getCheckedRadioButtonId();
//
//        RadioButton radioButtonGameCharacter = (RadioButton) DonateFragment.this.findViewById(gameCharacter);
//
//        String message ="Game Character: " + radioButtonGameCharacter.getText() ;
//
//        Toast.makeText(DonateFragment.this,message,Toast.LENGTH_LONG).show();
//    }

    public void pickDate() {
        calendar = Calendar.getInstance();
        int date = calendar.get(Calendar.DATE);
        int month = calendar.get(Calendar.MONTH);
        int year = calendar.get(Calendar.YEAR);
        DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(year, month, dayOfMonth);
                @SuppressLint("SimpleDateFormat") SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
                Calendar now = Calendar.getInstance();
                if (calendar.getTime().after(now.getTime())) {
                    //chon qua thoi gian
                    Log.d("VuHung", "" + true);
                    Toast.makeText(getContext(), "Ngày sinh không hợp lệ", Toast.LENGTH_SHORT).show();
                } else {
                    //chon dung thoi gian
                    Log.d("VuHung", "" + false);
                    txtNgaySinh.setText(simpleDateFormat.format(calendar.getTime()));
                }
            }
        }, year, month, date);
        datePickerDialog.show();
    }


    @Override
    public void onClick(View v) {

        if (v == btnDangKy) {
            final String name = edtTen.getText().toString();
            final String birthday = txtNgaySinh.getText().toString();
            final String phone = edtSDT.getText().toString();
            final String email = edtEmail.getText().toString();
            final String diachi = edtDiaChi.getText().toString();
            final String nghenghiep = edtNgheNghiep.getText().toString();
            final String CMND = edtCMND.getText().toString();
            final String gender;
            if (radio_Nam.isChecked()) {
                gender = radio_Nam.getText().toString();
            } else {
                gender = radio_Nu.getText().toString();
            }
            if (presenterLogicDonateBlood.checkInput(name, gender, birthday, phone, email, diachi, nghenghiep, CMND) == false) {

            } else {
                presenterLogicDonateBlood.ResolveRegisterDonateBlood(name, gender, birthday, phone, email, diachi, nghenghiep, CMND);
                presenterLogicDonateBlood.ResolveRegisterDonateBlood2(daTungHienMau, benhManTinh, sutCan, noiHach, chuaRang,
                        xamMinh, duocChuyenMau, maTuy, quanHeHIV, tiemVacXin, vungCoDich, biCum, dungThuocKhangSinh,
                        khamBacSy, chatDocDaCam, coThai);
                startActivity(new Intent(getContext(), PhieuDangKy.class));
            }
        }
    }

    @Override
    public void checkInput() {
        Toast.makeText(getContext(), "Khong duoc de trong", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void successfully() {

    }

    @Override
    public void unsuccessfully() {

    }
}



