package com.example.an.bloodgroupssearchingsystem.Presenter.UpdateInformation;

import android.net.Uri;
import android.widget.EditText;

import com.example.an.bloodgroupssearchingsystem.Model.Register.Customer;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public interface PresenterImpUpdate {
    void ResovleUpdate(EditText address, CircleImageView avatar, EditText fullname, EditText birthday, EditText gender, EditText phoneNumber);
    void ResovleLoadDataCustomer(Customer customer,EditText address, CircleImageView avatar, final EditText fullname, final EditText birthday, final EditText gender, final EditText phoneNumber);
    void LoadDataCustomer();
}
