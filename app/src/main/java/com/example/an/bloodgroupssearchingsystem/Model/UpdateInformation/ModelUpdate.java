package com.example.an.bloodgroupssearchingsystem.Model.UpdateInformation;

import android.support.annotation.NonNull;
import android.util.Log;

import com.example.an.bloodgroupssearchingsystem.Model.Register.Customer;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ModelUpdate {
    private FirebaseUser user=FirebaseAuth.getInstance().getCurrentUser();
    private DatabaseReference mData= FirebaseDatabase.getInstance().getReference();
    private LoadInformationCustomer loadInformationCustomer;
    public ModelUpdate(LoadInformationCustomer loadInformationCustomer){
        this.loadInformationCustomer=loadInformationCustomer;
    }
    public ModelUpdate(){

    }
    public void setUpdateInformation(String address, String avatar, String fullname, String birthday, String gender, String phoneNumber)
    {
        Customer customer=new Customer(address,avatar,fullname,birthday,gender,phoneNumber);
        mData.child("Customer").child(user.getUid()).setValue(customer);
    }
    public void LoadInformation(){
        final Customer customer=new Customer();
        mData.child("Customer").child(user.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                customer.setAddress(dataSnapshot.child("address").getValue().toString());
                customer.setAvatar(dataSnapshot.child("avatar").getValue().toString());
                customer.setBirthday(dataSnapshot.child("birthday").getValue().toString());
                customer.setFullname(dataSnapshot.child("fullname").getValue().toString());
                customer.setGender(dataSnapshot.child("gender").getValue().toString());
                customer.setPhoneNumber(dataSnapshot.child("phoneNumber").getValue().toString());
                Log.d("AAA","vao");
                loadInformationCustomer.onLoadInformationSuccess(customer);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        /*mData.child("Customer").child(user.getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                customer.setAddress(dataSnapshot.child("address").getValue().toString());
                customer.setAvatar(dataSnapshot.child("avatar").getValue().toString());
                customer.setBirthday(dataSnapshot.child("birthday").getValue().toString());
                customer.setFullname(dataSnapshot.child("fullname").getValue().toString());
                customer.setGender(dataSnapshot.child("gender").getValue().toString());
                customer.setPhoneNumber(dataSnapshot.child("phoneNumber").getValue().toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });*/
        Log.d("AAA",""+customer.getAddress());
        Log.d("AAA",customer.getFullname()+"");
    }
}
