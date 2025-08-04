package com.s23010605.saferoute;

public class ContactModel {
    private String id;
    private String name;
    private String mobileNo;
    private String email;

    public ContactModel(String id, String name, String mobileNo, String email) {
        this.id = id;
        this.name = name;
        this.mobileNo = mobileNo;
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public String getEmail() {
        return email;
    }
}