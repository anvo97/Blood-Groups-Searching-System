package com.example.an.bloodgroupssearchingsystem.Presenter.Register;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.widget.Toast;

import com.example.an.bloodgroupssearchingsystem.Model.Register.Customer;
import com.example.an.bloodgroupssearchingsystem.Model.Register.ModelRegister;
import com.example.an.bloodgroupssearchingsystem.View.Login.LoginActivity;
import com.example.an.bloodgroupssearchingsystem.View.Register.RegisterActivity;
import com.example.an.bloodgroupssearchingsystem.View.Register.ViewRegister;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PresenterLogicRegister implements PresenterImpRegister {
    private FirebaseAuth mAuth;
    private ViewRegister viewRegister;
    private ModelRegister modelRegister = new ModelRegister();
    private DatabaseReference mData;

    public PresenterLogicRegister(ViewRegister viewRegister) {
        this.viewRegister = viewRegister;
    }

    @Override
    public void ResolveRegister(final String Fullname, String Email, final String PhoneNumber, String Password, final String Address) {
        if (Fullname.equals("") || Email.equals("") || PhoneNumber.equals("") || Password.equals("") || Address.equals("")) {
            viewRegister.CheckInput(true);
        } else if (CheckPhone(PhoneNumber) == 0 || CheckName(Fullname) == 0 || CheckEmail(Email)==0) {
            viewRegister.Error(true);
        } else {
            mAuth = FirebaseAuth.getInstance();
            mData = FirebaseDatabase.getInstance().getReference();
            mAuth.createUserWithEmailAndPassword(Email, Password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                                if (user != null) {
                                    Customer customer = new Customer(Address, "https://firebasestorage.googleapis.com/v0/b/blood-groups-searching-s-6867f.appspot.com/o/bvs.jpg?alt=media&token=f6bd412f-48b9-402f-99aa-fa60b51f2d42"
                                            , Fullname, "null", "null", PhoneNumber);

                                    mData.child("Customer").child(user.getUid()).setValue(customer);
                                }
//                                modelRegister.setDataCustomer(Address,"https://firebasestorage.googleapis.com/v0/b/blood-groups-searching-s-6867f.appspot.com/o/bvs.jpg?alt=media&token=f6bd412f-48b9-402f-99aa-fa60b51f2d42"
//                                            ,Fullname,"null","null",PhoneNumber);
                                viewRegister.RegisterSuccess();
                            } else {
                                viewRegister.RegisterUnsuccess();
                            }
                        }
                    });
        }
    }

    public int CheckPhone(String phone) {
        int count = 0;
        for (int i = 0; i < phone.length(); i++) {
            if (Character.isWhitespace(phone.charAt(i))) {
                count++;
            }
        }
        if (phone.length() < 10 || phone.length() > 10 || count > 0) {
            return 0;
        } else {
            return 1;
        }
    }

    public int CheckName(String name) {
        int count = 0;
        for (int i = 0; i < name.length(); i++) {
            if (!Character.isAlphabetic(name.charAt(i)) && !Character.isWhitespace(name.charAt(i))) {
                count++;
            }
        }
        if (count > 0) return 0;
        else return 1;
    }

    public int CheckEmail(String email){
        int count=0;
        if (email.length()<16 || email.length()>40){
            count++;
        }
        String emailPattern = "^[\\w!#$%&’*+/=?`{|}~^-]+(?:\\.[\\w!#$%&’*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
        Pattern regex = Pattern.compile(emailPattern);
        Matcher matcher = regex.matcher(email);
        if (matcher.find() && count==0) {
            return 1;
        } else {
            return 0;
        }
    }
}
