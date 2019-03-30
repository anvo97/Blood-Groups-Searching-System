package com.example.an.bloodgroupssearchingsystem.View.UpdateInformation;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.an.bloodgroupssearchingsystem.Model.Register.Customer;
import com.example.an.bloodgroupssearchingsystem.Presenter.UpdateInformation.PresenterLogicUpdate;

import com.example.an.bloodgroupssearchingsystem.R;
import com.example.an.bloodgroupssearchingsystem.View.Login.LoginActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * A simple {@link Fragment} subclass.
 */
public class UserFragment extends Fragment implements ViewUpdate {
    private FrameLayout btnMyProfile,btnChangePW;
    private CircleImageView imgAvatarUser;
    private TextView txtFullnameUser,txtEmailUser;
    private FirebaseUser user;
    private PresenterLogicUpdate presenterLogicUpdate=new PresenterLogicUpdate(this);

    public UserFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_user,container, false);
//        btnMyProfile=(FrameLayout)view.findViewById(R.id.btnMyProfile);
//        btnChangePW=(FrameLayout)view.findViewById(R.id.btnChangePW);
//        imgAvatarUser=(CircleImageView)view.findViewById(R.id.imgAvatarUser);
//        txtFullnameUser=(TextView)view.findViewById(R.id.txtFullnameUser);
//        txtEmailUser=(TextView)view.findViewById(R.id.txtEmailUser);
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
}
