package com.example.an.bloodgroupssearchingsystem.View.Register;

import android.animation.ValueAnimator;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.wifi.WifiManager;
import android.os.Handler;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.an.bloodgroupssearchingsystem.Presenter.Register.PresenterLogicRegister;
import com.example.an.bloodgroupssearchingsystem.R;
import com.example.an.bloodgroupssearchingsystem.View.Login.LoginActivity;

public class RegisterActivity extends AppCompatActivity implements ViewRegister,View.OnClickListener{
    private EditText edtFullname,edtEmailRegister,edtPhoneNumber,edtPasswordRegister,edtAddress;
    private FrameLayout btnRegister;
    private TextView txtRegister,txtIntLogin;
    private ProgressBar progressbarRegister;
    private ImageButton btnShowPass, btnHidePass;
    private Boolean checkinput=false;

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
        btnHidePass = (ImageButton)findViewById(R.id.btn_eye_off);
        btnShowPass = (ImageButton)findViewById(R.id.btn_eye_on);
        edtAddress=(EditText)findViewById(R.id.edtAddress);

        btnShowPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtPasswordRegister.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                btnShowPass.setVisibility(View.INVISIBLE);
                btnHidePass.setVisibility(View.VISIBLE);
            }
        });

        btnHidePass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtPasswordRegister.setTransformationMethod(PasswordTransformationMethod.getInstance());
                btnShowPass.setVisibility(View.VISIBLE);
                btnHidePass.setVisibility(View.INVISIBLE);
            }
        });
    }

    @Override
    public void RegisterSuccess() {
        ResetButton();
        Toast.makeText(this, "Đăng ký thành công", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void RegisterUnsuccess() {
        ResetButton();
        Toast.makeText(this, "Đăng ký thất bại", Toast.LENGTH_SHORT).show();
    }

    private void ResetButton() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                ValueAnimator animator=ValueAnimator.ofInt(btnRegister.getMeasuredWidth(),ResetSizeButton());
                animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        int val=(Integer)animation.getAnimatedValue();
                        ViewGroup.LayoutParams layoutParams=btnRegister.getLayoutParams();
                        layoutParams.width=val;
                        btnRegister.requestLayout();
                    }
                });
                animator.setDuration(250);
                animator.start();
            }
        },25);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                txtRegister.animate().alpha(1f).start();
            }
        },25);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                progressbarRegister.setVisibility(View.INVISIBLE);
            }
        },25);
    }

    @Override
    public void CheckInput(Boolean CheckInput) {
        checkinput=CheckInput;
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
        final WifiManager wifiManager = (WifiManager) getApplicationContext().getSystemService(WIFI_SERVICE);
        if(v==btnRegister){
            if(wifiManager.isWifiEnabled()){
                presenterLogicRegister.ResolveRegister(edtFullname.getText().toString(),edtEmailRegister.getText().toString(),edtPhoneNumber.getText().toString()
                        ,edtPasswordRegister.getText().toString(),edtAddress.getText().toString());
                if(checkinput==false){
                    AnimatorButton();
                }
                checkinput=false;
            }
            else {
                AlertDialog.Builder alert = new AlertDialog.Builder(RegisterActivity.this);
                alert.setTitle("Thông báo");
                alert.setMessage("Vui lòng bật wifi trước khi đăng ký tài khoản");
                alert.setNegativeButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS));
                    }
                });
                alert.setPositiveButton("Hủy", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                alert.show();
            }
        }

        if(v==txtIntLogin){
            Intent intent=new Intent(RegisterActivity.this, LoginActivity.class);
            startActivity(intent);
        }
    }

    private void AnimatorButton() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                ValueAnimator animator=ValueAnimator.ofInt(btnRegister.getMeasuredWidth(),FixSizeButton());
                animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        int val=(Integer)animation.getAnimatedValue();
                        ViewGroup.LayoutParams layoutParams=btnRegister.getLayoutParams();
                        layoutParams.width=val;
                        btnRegister.requestLayout();
                    }
                });
                animator.setDuration(250);
                animator.start();
            }
        },25);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                txtRegister.animate().alpha(0f).start();
            }
        },25);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                progressbarRegister.setVisibility(View.VISIBLE);
            }
        },25);
    }
}
