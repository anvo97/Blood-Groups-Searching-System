package com.example.an.bloodgroupssearchingsystem.Presenter.UpdateInformation;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.an.bloodgroupssearchingsystem.Model.Register.Customer;
import com.example.an.bloodgroupssearchingsystem.Model.UpdateInformation.LoadInformationCustomer;
import com.example.an.bloodgroupssearchingsystem.Model.UpdateInformation.ModelUpdate;
import com.example.an.bloodgroupssearchingsystem.View.UpdateInformation.ViewUpdate;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.makeramen.roundedimageview.RoundedImageView;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Calendar;

import de.hdodenhof.circleimageview.CircleImageView;

public class PresenterLogicUpdate implements PresenterImpUpdate, LoadInformationCustomer {
    private ViewUpdate viewUpdate;
    private ModelUpdate modelUpdate=new ModelUpdate();
    private FirebaseStorage storage;
    private StorageReference storageRef;
    private FirebaseUser user=FirebaseAuth.getInstance().getCurrentUser();

    public PresenterLogicUpdate (ViewUpdate viewUpdate){
        this.viewUpdate=viewUpdate;
        modelUpdate=new ModelUpdate(this);
    }


    @Override
    public void ResovleUpdate(final EditText address, CircleImageView avatar, final EditText fullname, final EditText birthday, final EditText gender, final EditText phoneNumber) {
        storage = FirebaseStorage.getInstance();
        storageRef = storage.getReference();
        final StorageReference mountainsRef = storageRef.child("Customer").child("image"+user.getUid()+".png");
        avatar.setDrawingCacheEnabled(true);
        avatar.buildDrawingCache();
        Bitmap bitmap = ((BitmapDrawable) avatar.getDrawable()).getBitmap();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
        final byte[] data = baos.toByteArray();

        UploadTask uploadTask = mountainsRef.putBytes(data);
        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                Log.d("AAA","Error");
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                storageRef.child("Customer").child("image"+user.getUid()+".png").getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        if (user != null) {
                            modelUpdate.setUpdateInformation(address.getText().toString(),String.valueOf(uri),fullname.getText().toString(),birthday.getText().toString(),
                                    gender.getText().toString(),phoneNumber.getText().toString());
                            viewUpdate.UpdateSuccess();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        viewUpdate.UpdateUnsuccess();
                    }
                });
            }
        });
    }

    @Override
    public void ResovleLoadDataCustomer(Customer customer, EditText address, CircleImageView avatar, EditText fullname, EditText birthday, EditText gender, EditText phoneNumber) {
        address.setText(customer.getAddress());
        Picasso.get().load(customer.getAvatar()).into(avatar);
        fullname.setText(customer.getFullname());
        birthday.setText(customer.getBirthday());
        gender.setText(customer.getGender());
        phoneNumber.setText(customer.getPhoneNumber());
    }

    @Override
    public void LoadDataCustomer() {
        modelUpdate.LoadInformation();
    }


    @Override
    public void onLoadInformationSuccess(Customer dataCustomer) {
        viewUpdate.DisplayDataCustomer(dataCustomer);
    }
}
