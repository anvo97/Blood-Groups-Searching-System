package com.example.an.bloodgroupssearchingsystem.View.Donate;


import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
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
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    public DonateFragment() {
        // Required empty public constructor
    }

    private EditText edtTen, edtSDT, edtEmail, edtNgheNghiep, edtCMND;
    private TextView txtNgaySinh, txtDiaChi, txtDiaChi2;
    private Button btnNgaySinh, btnDangKy, btnDiaChi;
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
    private char[] kyTuDacBiet = {'-', '*', '!', '@', '~', '`', '#', '$', '%', '^', '&', '(', ')', '_', '+', ':', '{', '[', ']', '}', ';', '<', ',', '>', '.', '?', '/'};
    private boolean checkQuan = false;

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
        txtDiaChi = (TextView) view.findViewById(R.id.txtDiaChi);
        txtDiaChi2 = (TextView) view.findViewById(R.id.txtDiaChi2);
        txtNgaySinh = (TextView) view.findViewById(R.id.txt_NgaySinh);
        btnNgaySinh = (Button) view.findViewById(R.id.btn_NgaySinh);
        btnDangKy = (Button) view.findViewById(R.id.btn_dangky);
        btnDiaChi = (Button) view.findViewById(R.id.btnDiaChi);
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
        btnDiaChi.setOnClickListener(this);
        return view;
    }

    private void mapping() {
        spinnerList.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (spinnerList.getSelectedItem().toString().equals("Bệnh viện")) {
                    ChuongTrinh = (String) parent.getItemAtPosition(position);
                    rlSukien.setVisibility(View.GONE);
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
                    rlSukien.setVisibility(View.GONE);
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
                txtNgaySinh.setText(simpleDateFormat.format(calendar.getTime()));
            }
        }, year, month, date);
        datePickerDialog.getDatePicker().setMaxDate(calendar.getTime().getTime());
        datePickerDialog.show();
    }


    @Override
    public void onClick(View v) {

        if (v == btnDangKy) {
            final String Name = edtTen.getText().toString();
            final String DateOfBirth = txtNgaySinh.getText().toString();
            final String Phone = edtSDT.getText().toString();
            final String Email = edtEmail.getText().toString();
            final String Diachi = txtDiaChi.getText().toString();
            final String NgheNghiep = edtNgheNghiep.getText().toString();
            final String CMND = edtCMND.getText().toString();
            final String Gender;
            if (radio_Nam.isChecked()) {
                Gender = radio_Nam.getText().toString();
            } else {
                Gender = radio_Nu.getText().toString();
            }
            if (presenterLogicDonateBlood.checkInput(ChuongTrinh, Name, Gender, DateOfBirth, Phone, Email, Diachi, NgheNghiep, CMND) == false) {
                sharedPreferences = getContext().getSharedPreferences("ADDRESS", getContext().MODE_PRIVATE);
                editor = sharedPreferences.edit();
                editor.clear();
                editor.commit();
            } else {
                presenterLogicDonateBlood.ResolveRegisterDonateBlood(ChuongTrinh, Name, Gender, DateOfBirth, Phone, Email, Diachi, NgheNghiep, CMND);
                presenterLogicDonateBlood.ResolveRegisterDonateBlood2(DaTungHienMau, BenhManTinh, SutCan, NoiHach, ChuaRang, XamMinh, DuocChuyenMau, MaTuy,
                        QuanHeHIV, TiemVacXin, VungCoDich, BiCum, DungThuocKhangSinh, KhamBacSy, ChatDocDaCam, CoThai);
                startActivity(new Intent(getContext(), PhieuDangKy.class));
                sharedPreferences = getContext().getSharedPreferences("ADDRESS", getContext().MODE_PRIVATE);
                editor = sharedPreferences.edit();
                editor.clear();
                editor.commit();
            }
        }
        if (v == btnDiaChi) {
            dialogAddress();
        }
    }

    public void dialogAddress() {
        final Dialog dialog = new Dialog(getContext());
        dialog.setContentView(R.layout.custom_edit_address);

        final EditText edtStreet = (EditText) dialog.findViewById(R.id.edtStreet);
        final EditText edtPhuong = (EditText) dialog.findViewById(R.id.edtPhuong);
        final EditText edtCounty = (EditText) dialog.findViewById(R.id.edtCounty);
        final EditText edtCity = (EditText) dialog.findViewById(R.id.edtCity);
        Button btnCancel = (Button) dialog.findViewById(R.id.btn_Cancel);
        Button btnOK = (Button) dialog.findViewById(R.id.btn_OK);

        sharedPreferences = getContext().getSharedPreferences("ADDRESS", getContext().MODE_PRIVATE);
        edtStreet.setText(sharedPreferences.getString("Street", ""));
        edtPhuong.setText(sharedPreferences.getString("Phuong", ""));
        edtCounty.setText(sharedPreferences.getString("County", ""));
        edtCity.setText(sharedPreferences.getString("City", ""));

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });

        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edtStreet.getText().length() == 0 || edtPhuong.getText().length() == 0
                        || edtCounty.getText().length() == 0 || edtCity.getText().length() == 0) {
                    Toast.makeText(getContext(), "Không được để trống", Toast.LENGTH_SHORT).show();
                } else {
                    char a[] = edtCounty.getText().toString().toCharArray();
                    for (int i = 0; i < a.length; i++) {
                        for (int j = 0; j < kyTuDacBiet.length; j++) {
                            if (a[i] == kyTuDacBiet[j]) {
                                checkQuan = true;
                            }
                        }
                    }
                    if (checkQuan) {
                        Toast.makeText(getContext(), "Không hợp lệ", Toast.LENGTH_SHORT).show();
                    } else {
                        String strStreet = edtStreet.getText().toString().toUpperCase();
                        String strPhuong = edtPhuong.getText().toString().toUpperCase();
                        String strCounty = edtCounty.getText().toString().toUpperCase();
                        String strCity = edtCity.getText().toString().toUpperCase();
                        txtDiaChi.setText(strStreet + ", " + strPhuong + ", " + strCounty + ", " + strCity);
                        String catChuoi = edtStreet.getText().toString() + ", " + edtPhuong.getText().toString()
                                + ", " + edtCounty.getText().toString() + ", " + edtCity.getText().toString();
                        txtDiaChi2.setText(catChuoi.substring(0, 25)+"...");
                        sharedPreferences = getContext().getSharedPreferences("ADDRESS", getContext().MODE_PRIVATE);
                        editor = sharedPreferences.edit();
                        editor.putString("Street", edtStreet.getText().toString());
                        editor.putString("Phuong", edtPhuong.getText().toString());
                        editor.putString("County", edtCounty.getText().toString());
                        editor.putString("City", edtCity.getText().toString());
                        editor.commit();
                        dialog.cancel();
                    }
                }
            }
        });

        dialog.show();
    }

    @Override
    public void checkInput() {
        Toast.makeText(getContext(), "Không được để trống", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void successfully() {

    }

    @Override
    public void unsuccessfully() {

    }



    @Override
    public void onPause() {
        super.onPause();
        rlSukien.setVisibility(View.GONE);
        spinnerList.setSelection(0);
    }
}



