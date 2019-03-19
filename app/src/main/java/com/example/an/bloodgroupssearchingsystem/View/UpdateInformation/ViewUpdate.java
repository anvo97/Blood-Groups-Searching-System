package com.example.an.bloodgroupssearchingsystem.View.UpdateInformation;

import com.example.an.bloodgroupssearchingsystem.Model.Register.Customer;

public interface ViewUpdate {
    void UpdateSuccess();
    void UpdateUnsuccess();
    void CheckInput(Boolean CheckInput);
    void DisplayDataCustomer(Customer customer);
}
