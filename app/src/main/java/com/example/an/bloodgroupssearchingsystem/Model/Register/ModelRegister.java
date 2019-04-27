package com.example.an.bloodgroupssearchingsystem.Model.Register;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ModelRegister {
    private DatabaseReference mData;
    private FirebaseAuth mAuth;
    FirebaseUser user=FirebaseAuth.getInstance().getCurrentUser();
    public void setDataCustomer(String address, String avatar, String fullname, String birthday, String gender, String phoneNumber){
        mData= FirebaseDatabase.getInstance().getReference();
        mAuth=FirebaseAuth.getInstance();
        Customer customer=new Customer(address,avatar,fullname,birthday,gender,phoneNumber);
        mData.child("Customer").child(user.getUid()).setValue(customer);
    }
}
