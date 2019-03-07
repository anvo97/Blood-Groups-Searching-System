package com.example.an.bloodgroupssearchingsystem.Presenter.Login;

public class Customers {
    String UserName;
    String Password;

    public Customers(String userName, String password) {
        UserName = userName;
        Password = password;
    }
    public Customers()
    {

    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }
}
