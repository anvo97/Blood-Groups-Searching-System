package com.example.an.bloodgroupssearchingsystem.Presenter.Register;

import android.support.annotation.NonNull;

import com.example.an.bloodgroupssearchingsystem.View.Register.ViewRegister;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class PresenterLogicRegister implements PresenterImpRegister {
    private FirebaseAuth mAuth;
    private ViewRegister viewRegister;
    public PresenterLogicRegister(ViewRegister viewRegister){
        this.viewRegister=viewRegister;
    }
    @Override
    public void ResolveRegister(String Fullname, String Email, String PhoneNumber, String Password) {
        if(Fullname.equals("")||Email.equals("")||PhoneNumber.equals("")||Password.equals("")){
            viewRegister.CheckInput(true);
        }else {
            mAuth=FirebaseAuth.getInstance();
            mAuth.createUserWithEmailAndPassword(Email,Password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                viewRegister.RegisterSuccess();
                            }else {
                                viewRegister.RegisterUnsuccess();
                            }
                        }
                    });
        }
    }
}
