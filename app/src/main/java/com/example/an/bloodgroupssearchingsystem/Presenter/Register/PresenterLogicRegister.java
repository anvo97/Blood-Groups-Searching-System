package com.example.an.bloodgroupssearchingsystem.Presenter.Register;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;

import com.example.an.bloodgroupssearchingsystem.Model.Register.ModelRegister;
import com.example.an.bloodgroupssearchingsystem.View.Register.ViewRegister;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class PresenterLogicRegister implements PresenterImpRegister {
    private FirebaseAuth mAuth;
    private ViewRegister viewRegister;
    private ModelRegister modelRegister=new ModelRegister();
    public PresenterLogicRegister(ViewRegister viewRegister){
        this.viewRegister=viewRegister;
    }
    @Override
    public void ResolveRegister(final String Fullname, String Email, final String PhoneNumber, String Password, final String Address) {
        if(Fullname.equals("")||Email.equals("")||PhoneNumber.equals("")||Password.equals("")){
            viewRegister.CheckInput(true);
        }else {
            mAuth=FirebaseAuth.getInstance();
            mAuth.createUserWithEmailAndPassword(Email,Password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                modelRegister.setDataCustomer(Address,"https://firebasestorage.googleapis.com/v0/b/blood-groups-searching-s-6867f.appspot.com/o/bvs.jpg?alt=media&token=f6bd412f-48b9-402f-99aa-fa60b51f2d42"
                                            ,Fullname,"null","null",PhoneNumber);
                                viewRegister.RegisterSuccess();
                            }else {
                                viewRegister.RegisterUnsuccess();
                            }
                        }
                    });
        }
    }
}
