package com.example.an.bloodgroupssearchingsystem.Model.Register;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ModelRegister {
    private DatabaseReference mData;

    public void setDataCustomer(String address, String avatar, String fullname, String birthday, String gender, String phoneNumber){
        mData= FirebaseDatabase.getInstance().getReference();
        Customer customer=new Customer(address,avatar,fullname,birthday,gender,phoneNumber);
        mData.child("Customer").push().setValue(customer);
    }
}
