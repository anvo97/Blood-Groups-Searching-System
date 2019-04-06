package com.example.an.bloodgroupssearchingsystem.View.UpdateInformation;


import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.an.bloodgroupssearchingsystem.Model.Register.Customer;
import com.example.an.bloodgroupssearchingsystem.Presenter.UpdateInformation.PresenterLogicUpdate;

import com.example.an.bloodgroupssearchingsystem.R;
import com.example.an.bloodgroupssearchingsystem.View.Login.LoginActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * A simple {@link Fragment} subclass.
 */
public class UserFragment extends Fragment implements ViewUpdate {
    private FrameLayout btnMyProfile,btnChangePW,btnInformationBlood;
    private CircleImageView imgAvatarUser;
    private TextView txtFullnameUser,txtEmailUser;
    private FirebaseUser user;
    private FirebaseAuth mAuth;
    private PresenterLogicUpdate presenterLogicUpdate=new PresenterLogicUpdate(this);

    public UserFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_user,container, false);
        btnMyProfile=(FrameLayout)view.findViewById(R.id.btnMyProfile);
        btnChangePW=(FrameLayout)view.findViewById(R.id.btnChangePW);
        btnInformationBlood=(FrameLayout)view.findViewById(R.id.btnInformationBlood);
        imgAvatarUser=(CircleImageView)view.findViewById(R.id.imgAvatarUser);
        txtFullnameUser=(TextView)view.findViewById(R.id.txtFullnameUser);
        txtEmailUser=(TextView)view.findViewById(R.id.txtEmailUser);
        user= FirebaseAuth.getInstance().getCurrentUser();
        btnMyProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(container.getContext(),MyProfileActivity.class);
                startActivity(intent);
            }
        });
        btnChangePW.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogChangePW();

            }
        });
        btnInformationBlood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intent=new Intent(getContext(), LoginActivity.class);
                startActivity(intent);
            }
        });
        presenterLogicUpdate.LoadDataCustomer();
        return view;
    }

    @Override
    public void UpdateSuccess() {

    }

    @Override
    public void UpdateUnsuccess() {

    }

    @Override
    public void CheckInput(Boolean CheckInput) {

    }

    @Override
    public void DisplayDataCustomer(Customer customer) {
        Picasso.get().load(customer.getAvatar()).into(imgAvatarUser);
        txtFullnameUser.setText(customer.getFullname());
        txtEmailUser.setText(user.getEmail());
    }
    private void DialogChangePW() {
        Dialog dialog = new Dialog(getContext());
        dialog.setContentView(R.layout.dialog_changepw_customs);
        final EditText edtNewPW = (EditText) dialog.findViewById(R.id.edtNewPW);
        final EditText edtVerifyPW = (EditText) dialog.findViewById(R.id.edtVerifyPW);
        Button btnSave = (Button) dialog.findViewById(R.id.btnSave);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth = FirebaseAuth.getInstance();
                if(edtNewPW.getText().toString().equals("")|| edtVerifyPW.getText().toString().equals(""))
                {
                    Toast.makeText(getContext(), "Không được để trống thông tin", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    if(!edtNewPW.getText().toString().equals(edtVerifyPW.getText().toString()))
                    {
                        Toast.makeText(getContext(), "Mật khẩu mới không khớp", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        mAuth.updatePassword(edtNewPW.getText().toString()).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()){
                                    Toast.makeText(getContext(), "cập nhật thành công", Toast.LENGTH_SHORT).show();

                                }else {
                                    Toast.makeText(getContext(), "cập nhật thất bại", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }

                }
            }
        });

        dialog.show();
    }
}
