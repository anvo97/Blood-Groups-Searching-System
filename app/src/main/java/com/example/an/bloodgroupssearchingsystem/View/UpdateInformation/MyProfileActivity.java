package com.example.an.bloodgroupssearchingsystem.View.UpdateInformation;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.bigkoo.svprogresshud.SVProgressHUD;
import com.example.an.bloodgroupssearchingsystem.Model.Register.Customer;
import com.example.an.bloodgroupssearchingsystem.Presenter.UpdateInformation.PresenterLogicUpdate;
import com.example.an.bloodgroupssearchingsystem.R;
import com.github.florent37.shapeofview.shapes.CircleView;
import com.makeramen.roundedimageview.RoundedImageView;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import de.hdodenhof.circleimageview.CircleImageView;

public class MyProfileActivity extends AppCompatActivity implements View.OnClickListener,ViewUpdate{
    private EditText edtFullname,edtBirthday,edtPhoneNumber,edtGender,edtAddress;
    private CircleImageView imgAvatar;
    private FrameLayout btnUpdate;
    int PICK_IMAGE=1;
    private PresenterLogicUpdate presenterLogicUpdate;
    private SVProgressHUD mSvProgressHUD;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);
        AnhXa();
        mSvProgressHUD=new SVProgressHUD(this);
        presenterLogicUpdate=new PresenterLogicUpdate(this);
        imgAvatar.setOnClickListener(this);
        edtBirthday.setOnClickListener(this);
        btnUpdate.setOnClickListener(this);
        presenterLogicUpdate.LoadDataCustomer();
    }

    private void AnhXa() {
        edtFullname=(EditText)findViewById(R.id.editTen);
        edtBirthday=(EditText)findViewById(R.id.editBirthday);
        edtPhoneNumber=(EditText)findViewById(R.id.editPhoneNumber);
        edtGender=(EditText)findViewById(R.id.editGender);
        edtAddress=(EditText)findViewById(R.id.editAddress);
        imgAvatar=(CircleImageView) findViewById(R.id.imgAvatar);
        btnUpdate=(FrameLayout)findViewById(R.id.btnUpdate);
    }
    public void PickAvatar(){
        Intent intent=new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,"Select Picture"),PICK_IMAGE);
    }
    public void PickBirthday(){
        final Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DATE);
        int month = calendar.get(Calendar.MONTH);
        int year = calendar.get(Calendar.YEAR);
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
                calendar.set(year, month, dayOfMonth);
                Calendar now = Calendar.getInstance();

                if (calendar.getTime().after(now.getTime())) {
                    //chon qua thoi gian
                    Log.d("VuHung", "" + true);
                    Toast.makeText(MyProfileActivity.this, "Ngày sinh không hợp lệ", Toast.LENGTH_SHORT).show();
                } else {
                    //chon dung thoi gian
                    Log.d("VuHung", "" + false);
                    edtBirthday.setText(simpleDateFormat.format(calendar.getTime()));
                }
            }
        }, year, month, day);
        datePickerDialog.show();
    }

    @Override
    public void onClick(View v) {
        if(v==imgAvatar){
            PickAvatar();
        }
        if(v==edtBirthday)
        {
            PickBirthday();
        }
        if (v==btnUpdate){
            mSvProgressHUD.show();
            presenterLogicUpdate.ResovleUpdate(edtAddress,imgAvatar,edtFullname,edtBirthday,edtGender,edtPhoneNumber) ;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==PICK_IMAGE && resultCode==RESULT_OK && data!=null){
            imgAvatar.setImageURI(data.getData());
        }
    }

    @Override
    public void UpdateSuccess() {
        mSvProgressHUD.dismiss();
        Toast.makeText(this, "Cập nhật thành công", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void UpdateUnsuccess() {
        mSvProgressHUD.dismiss();
        Toast.makeText(this, "Cập nhật thất bại", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void CheckInput(Boolean CheckInput) {
        mSvProgressHUD.dismiss();
        Toast.makeText(this, "Vui lòng kiểm tra lại thông tin", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void DisplayDataCustomer(Customer customer) {
        presenterLogicUpdate.ResovleLoadDataCustomer(customer,edtAddress,imgAvatar,edtFullname,edtBirthday,edtGender,edtPhoneNumber);
    }
}
