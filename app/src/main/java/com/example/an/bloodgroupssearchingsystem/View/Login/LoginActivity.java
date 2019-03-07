package com.example.an.bloodgroupssearchingsystem.View.Login;

import android.animation.ValueAnimator;
import android.content.Intent;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.an.bloodgroupssearchingsystem.Presenter.Login.PresenterLogicLogin;
import com.example.an.bloodgroupssearchingsystem.R;
import com.example.an.bloodgroupssearchingsystem.View.Register.RegisterActivity;

public class LoginActivity extends AppCompatActivity implements ViewLogin,View.OnClickListener {
    private FrameLayout btnLogin;
    private EditText edtUserName,edtPassword;
    private TextView txtLogin,txtIntRegister;
    private ProgressBar progressBar;


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
    }

    @Override
    public void onClick(View v) {
        if(v==btnLogin){
            presenterLogicLogin.ResolveLogin(edtUserName.getText().toString(),edtPassword.getText().toString());
            if(checkinput==false){
                AnimatorButton();
            }
            checkinput=false;
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
