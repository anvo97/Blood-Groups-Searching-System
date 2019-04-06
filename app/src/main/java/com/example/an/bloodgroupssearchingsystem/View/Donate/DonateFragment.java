package com.example.an.bloodgroupssearchingsystem.View.Donate;


import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.an.bloodgroupssearchingsystem.Presenter.Donate.PresenterLogicDonateBlood;
import com.example.an.bloodgroupssearchingsystem.R;
import com.example.an.bloodgroupssearchingsystem.View.UpdateInformation.MyProfileActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
    private boolean DaTungHienMau, BenhManTinh, SutCan, NoiHach, ChuaRang, XamMinh, DuocChuyenMau, MaTuy,
            QuanHeHIV, TiemVacXin, VungCoDich, BiCum, DungThuocKhangSinh, KhamBacSy, ChatDocDaCam, CoThai;
    private Spinner spinnerList, spinnerListSuKien;
    private ArrayList<String> arraySpinner, arraySpinnerSuKien;
    private ArrayAdapter<String> arrayAdapter, arrayAdapterSuKien;
    private RelativeLayout rlSukien;
    private DatabaseReference mData;
    private String ChuongTrinh;

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
        rlSukien = (RelativeLayout) view.findViewById(R.id.rlSuKien);
        mData = FirebaseDatabase.getInstance().getReference();

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

        spinnerList = (Spinner) view.findViewById(R.id.spinner);
        arraySpinner = new ArrayList<String>();

        arraySpinner.add("Bệnh viện");
        arraySpinner.add("Sự kiện");
        arraySpinner.add("Tư nhân");


        arrayAdapter = new ArrayAdapter<>(getContext(), R.layout.spinner_textview, arraySpinner);
        arrayAdapter.setDropDownViewResource(R.layout.spinner_textview);

        spinnerList.setAdapter(arrayAdapter);

        spinnerListSuKien = (Spinner) view.findViewById(R.id.spinnerSuKien);
        arraySpinnerSuKien = new ArrayList<String>();

        mData.child("Event").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Iterable<DataSnapshot> nodeChild = dataSnapshot.getChildren();
                for (DataSnapshot child : nodeChild) {
                    Event event = child.getValue(Event.class);
                    arraySpinnerSuKien.add(event.Name);
                }
                arrayAdapterSuKien = new ArrayAdapter<>(getContext(), R.layout.spinner_textview, arraySpinnerSuKien);
                arrayAdapterSuKien.setDropDownViewResource(R.layout.spinner_textview);
                spinnerListSuKien.setAdapter(arrayAdapterSuKien);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        mapping();

        btnDangKy.setOnClickListener(this);
        return view;
    }

    private void mapping() {
        spinnerList.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (spinnerList.getSelectedItem().toString().equals("Bệnh viện")) {
                    ChuongTrinh = (String) parent.getItemAtPosition(position);
                    rlSukien.setVisibility(View.INVISIBLE);
                    spinnerList.setSelection(position);
                } else if (spinnerList.getSelectedItem().toString().equals("Sự kiện")) {
                    rlSukien.setVisibility(View.VISIBLE);
                    spinnerList.setSelection(position);
                    ChuongTrinh = "Sự kiện: " + spinnerListSuKien.getSelectedItem().toString();
                    spinnerListSuKien.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int po, long id) {
                            ChuongTrinh = "Sự kiện: " + (String) parent.getItemAtPosition(po);
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });
                } else {
                    ChuongTrinh = (String) parent.getItemAtPosition(position);
                    rlSukien.setVisibility(View.INVISIBLE);
                    spinnerList.setSelection(position);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void doOnGameCharacterChanged(CompoundButton buttonView, boolean isChecked) {
        RadioButton radio = (RadioButton) buttonView;
    }

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
            final String Name = edtTen.getText().toString();
            final String DateOfBirth = txtNgaySinh.getText().toString();
            final String Phone = edtSDT.getText().toString();
            final String Email = edtEmail.getText().toString();
            final String Diachi = edtDiaChi.getText().toString();
            final String NgheNghiep = edtNgheNghiep.getText().toString();
            final String CMND = edtCMND.getText().toString();
            final String Gender;
            if (radio_Nam.isChecked()) {
                Gender = radio_Nam.getText().toString();
            } else {
                Gender = radio_Nu.getText().toString();
            }
            if (presenterLogicDonateBlood.checkInput(ChuongTrinh, Name, Gender, DateOfBirth, Phone, Email, Diachi, NgheNghiep, CMND) == false) {

            } else {
                presenterLogicDonateBlood.ResolveRegisterDonateBlood(ChuongTrinh, Name, Gender, DateOfBirth, Phone, Email, Diachi, NgheNghiep, CMND);
                presenterLogicDonateBlood.ResolveRegisterDonateBlood2(DaTungHienMau, BenhManTinh, SutCan, NoiHach, ChuaRang, XamMinh, DuocChuyenMau, MaTuy,
                        QuanHeHIV, TiemVacXin, VungCoDich, BiCum, DungThuocKhangSinh, KhamBacSy, ChatDocDaCam, CoThai);
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



