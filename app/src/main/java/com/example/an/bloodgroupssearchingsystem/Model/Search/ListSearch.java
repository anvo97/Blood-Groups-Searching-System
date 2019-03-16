package com.example.an.bloodgroupssearchingsystem.Model.Search;

public class ListSearch {
    private String phoneHospital;
    private String addressHospital;
    private String phoneCustomer;

    public ListSearch(String phoneHospital, String addressHospital, String phoneCustomer) {
        this.phoneHospital = phoneHospital;
        this.addressHospital = addressHospital;
        this.phoneCustomer = phoneCustomer;
    }

    public String getPhoneHospital() {
        return phoneHospital;
    }

    public void setPhoneHospital(String phoneHospital) {
        this.phoneHospital = phoneHospital;
    }

    public String getAddressHospital() {
        return addressHospital;
    }

    public void setAddressHospital(String addressHospital) {
        this.addressHospital = addressHospital;
    }

    public String getPhoneCustomer() {
        return phoneCustomer;
    }

    public void setPhoneCustomer(String phoneCustomer) {
        this.phoneCustomer = phoneCustomer;
    }
}
