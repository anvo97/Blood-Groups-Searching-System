package com.example.an.bloodgroupssearchingsystem.Model.Search;

public class ListSearch {
    private String Name;
    private String Phone;

    public ListSearch(){

    }

    public ListSearch(String name, String phone) {
        Name = name;
        Phone = phone;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }
}
