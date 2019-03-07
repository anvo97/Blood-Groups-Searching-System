package com.example.an.bloodgroupssearchingsystem.Presenter.Login;

import android.support.annotation.NonNull;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.an.bloodgroupssearchingsystem.View.Login.ViewLogin;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class PresenterLogicLogin implements PresenterImpLogin {
    private FirebaseAuth mAuth;
    private ViewLogin viewLogin;
    public PresenterLogicLogin(ViewLogin viewLogin){
        this.viewLogin=viewLogin;
    }
    @Override
    public void ResolveLogin(String Username, String Password) {
        if(Username.equals("") || Password.equals("")){
            viewLogin.CheckInput(true);
        }else {
            mAuth=FirebaseAuth.getInstance();
            mAuth.signInWithEmailAndPassword(Username,Password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()){
                                viewLogin.LoginSuccess();
                            }else {
                                viewLogin.LoginUnsuccess();
                            }
                        }
                    });
        }

    }

}
