package com.example.an.bloodgroupssearchingsystem.View.Register;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.an.bloodgroupssearchingsystem.Presenter.Register.PresenterLogicRegister;
import com.example.an.bloodgroupssearchingsystem.R;
import com.example.an.bloodgroupssearchingsystem.View.Login.LoginActivity;

public class RegisterActivity extends AppCompatActivity implements ViewRegister,View.OnClickListener{
    private EditText edtFullname,edtEmailRegister,edtPhoneNumber,edtPasswordRegister;
    private FrameLayout btnRegister;
    private TextView txtRegister,txtIntLogin;
    private ProgressBar progressbarRegister;

    private PresenterLogicRegister presenterLogicRegister;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        AnhXa();
        presenterLogicRegister=new PresenterLogicRegister(this);
        btnRegister.setOnClickListener(this);
        txtIntLogin.setOnClickListener(this);

    }

    private void AnhXa() {
        edtFullname=(EditText) findViewById(R.id.edtFullname);
        edtEmailRegister=(EditText)findViewById(R.id.edtEmailRegister);
        edtPasswordRegister=(EditText)findViewById(R.id.edtPasswordRegister);
        edtPhoneNumber=(EditText)findViewById(R.id.edtPhoneNumber);
        btnRegister=(FrameLayout)findViewById(R.id.btnRegister);
        txtRegister=(TextView)findViewById(R.id.txtRegister);
        progressbarRegister=(ProgressBar)findViewById(R.id.progressbarRegister);
        txtIntLogin=(TextView)findViewById(R.id.txtIntLogin);
    }

    @Override
    public void RegisterSuccess() {
        Toast.makeText(this, "Đăng ký thành công", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void RegisterUnsuccess() {
        Toast.makeText(this, "Đăng ký thất bại", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void CheckInput(Boolean CheckInput) {
        Toast.makeText(this, "vui lòng không được để trống", Toast.LENGTH_SHORT).show();
    }
    public int FixSizeButton(){
        return (int) getResources().getDimension(R.dimen.fix_size);
    }
    public int ResetSizeButton(){
        return (int) getResources().getDimension(R.dimen.reset_size);
    }

    @Override
    public void onClick(View v) {
        if(v==btnRegister){
            presenterLogicRegister.ResolveRegister(edtFullname.getText().toString(),edtEmailRegister.getText().toString(),edtPhoneNumber.getText().toString(),edtPasswordRegister.getText().toString());
        }
        if(v==txtIntLogin){
            Intent intent=new Intent(RegisterActivity.this, LoginActivity.class);
            startActivity(intent);
        }
    }
}
