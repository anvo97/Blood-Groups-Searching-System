package com.example.an.bloodgroupssearchingsystem.View.Login;

import android.animation.ValueAnimator;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;
import android.os.Handler;
import android.provider.Settings;
import android.support.annotation.Nullable;
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

import com.example.an.bloodgroupssearchingsystem.Presenter.Login.PresenterLogicLogin;
import com.example.an.bloodgroupssearchingsystem.R;
import com.example.an.bloodgroupssearchingsystem.View.Menu.MenuActivity;
import com.example.an.bloodgroupssearchingsystem.View.Register.RegisterActivity;

public class LoginActivity extends AppCompatActivity implements ViewLogin,View.OnClickListener {
    private FrameLayout btnLogin;
    private EditText edtUserName,edtPassword;
    private TextView txtLogin,txtIntRegister;
    private ProgressBar progressBar;
    private ImageButton btnShowPass, btnHidePass;

    private PresenterLogicLogin presenterLogicLogin;
    private Boolean checkinput=false;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        AnhXa();
        presenterLogicLogin=new PresenterLogicLogin(this);
        btnLogin.setOnClickListener(this);
        txtIntRegister.setOnClickListener(this);
    }

    private void AnhXa() {
        btnLogin    =(FrameLayout)  findViewById(R.id.btnButton);
        edtUserName =(EditText)     findViewById(R.id.edtUserName);
        edtPassword =(EditText)     findViewById(R.id.edtPassword);
        txtLogin    =(TextView)     findViewById(R.id.txtlogin);
        progressBar =(ProgressBar)  findViewById(R.id.progressbar);
        txtIntRegister=(TextView)   findViewById(R.id.txtIntRegister);
        btnHidePass = (ImageButton)findViewById(R.id.btn_eye_off);
        btnShowPass = (ImageButton)findViewById(R.id.btn_eye_on);

        btnShowPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                btnShowPass.setVisibility(View.INVISIBLE);
                btnHidePass.setVisibility(View.VISIBLE);
            }
        });

        btnHidePass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                btnShowPass.setVisibility(View.VISIBLE);
                btnHidePass.setVisibility(View.INVISIBLE);
            }
        });

    }

    @Override
    public void onClick(View v) {
        final WifiManager wifi = (WifiManager)getApplicationContext().getSystemService(WIFI_SERVICE);
        if(v==btnLogin){
            if(wifi.isWifiEnabled()){
                presenterLogicLogin.ResolveLogin(edtUserName.getText().toString(),edtPassword.getText().toString());
                if(checkinput==false){
                    AnimatorButton();
                }
                checkinput=false;
            }
            else {
                AlertDialog.Builder alert =new AlertDialog.Builder(LoginActivity.this);
                alert.setTitle("Thông báo");
                alert.setMessage("Vui lòng mở wifi trước khi đăng nhập vào ứng dụng");
                alert.setPositiveButton("Hủy", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                alert.setNegativeButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS));
                    }
                });
                alert.show();
            }
        }

        if(v==txtIntRegister){
            Intent intent=new Intent(LoginActivity.this, RegisterActivity.class);
            startActivity(intent);
            finish();
        }
    }

    private void ResetButton() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                ValueAnimator animator=ValueAnimator.ofInt(btnLogin.getMeasuredWidth(),ResetSizeButton());
                animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        int val=(Integer)animation.getAnimatedValue();
                        ViewGroup.LayoutParams layoutParams=btnLogin.getLayoutParams();
                        layoutParams.width=val;
                        btnLogin.requestLayout();
                    }
                });
                animator.setDuration(250);
                animator.start();
            }
        },25);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                txtLogin.animate().alpha(1f).start();
            }
        },25);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                progressBar.setVisibility(View.INVISIBLE);
            }
        },25);
    }

    private void AnimatorButton() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                ValueAnimator animator=ValueAnimator.ofInt(btnLogin.getMeasuredWidth(),FixSizeButton());
                animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        int val=(Integer)animation.getAnimatedValue();
                        ViewGroup.LayoutParams layoutParams=btnLogin.getLayoutParams();
                        layoutParams.width=val;
                        btnLogin.requestLayout();
                    }
                });
                animator.setDuration(250);
                animator.start();
            }
        },25);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                txtLogin.animate().alpha(0f).start();
            }
        },25);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                progressBar.setVisibility(View.VISIBLE);
            }
        },25);
    }

    @Override
    public void LoginSuccess() {
        Toast.makeText(this, "Đăng Nhập Thành Công", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(LoginActivity.this, MenuActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void LoginUnsuccess() {
        ResetButton();
        Toast.makeText(this, "Đăng Nhập Thất Bại", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void CheckInput(Boolean c) {
        checkinput=c;
        Toast.makeText(this, "Vui Lòng không để trống!", Toast.LENGTH_SHORT).show();

    }

    public int FixSizeButton(){
        return (int) getResources().getDimension(R.dimen.fix_size);
    }
    public int ResetSizeButton(){
        return (int) getResources().getDimension(R.dimen.reset_size);
    }
}
