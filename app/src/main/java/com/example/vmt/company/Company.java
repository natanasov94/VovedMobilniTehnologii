package com.example.vmt.company;

public class Company {

    private String logoPath;
    private String name;
    private String address;
    private String phone;
    private String site;

    public Company(String logoPath, String name, String address, String phone, String site) {
        this.logoPath = logoPath;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.site = site;
    }

    public String getLogoPath() {
        return logoPath;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public String getSite() {
        return site;
    }
}
